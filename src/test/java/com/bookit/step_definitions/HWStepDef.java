package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.utilities.BookItApiUtils;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.DBUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

import java.util.Map;

public class HWStepDef {



    @And("I get the current user information from UI")
    public void iGetTheCurrentUserInformationFromUI() {
        SelfPage selfPage = new SelfPage();
        BrowserUtils.waitFor(3);
        String actualUIFullName = selfPage.name.getText();
        String actualUIRole = selfPage.role.getText();
        String actualUITeam = selfPage.team.getText();
        String actualUIBatch = selfPage.batch.getText();
        String actualUICampus = selfPage.campus.getText();

        System.out.println("actualUICampus = " + actualUICampus);
        System.out.println("actualUIBatch = " + actualUIBatch);

    }


    @And("I get the current user information from DataBase")
    public void iGetTheCurrentUserInformationFromDataBase() {

        String query = "select firstname,lastname,role,batch_number,name,location\n" +
                "from users u join (select t.name,t.id,t.batch_number,c.location \n" +
                "from team t join campus c on t.campus_id=c.id)j\n" +
                "on u.team_id = j.id where email='"+ApiStepDefs.emailGlobal+"';";

        Map<String,Object> dataMap = DBUtils.getRowMap(query);
        String nameFromDB = dataMap.get("firstname")+" "+dataMap.get("lastname");
        String roleFromDB = (String) dataMap.get("role");
        String batchFromDB = "#"+dataMap.get("batch_number");
        String teamNameFromDB = (String) dataMap.get("name");
        String locationFromDB = (String) dataMap.get("location");
        System.out.println("dataMap = " + dataMap);


    }




    @And("I get more information about user from API")
    public void iGetMoreInformationAboutUserFromAPI() {

        JsonPath jsonPath = ApiStepDefs.response.jsonPath();
        String nameFromAPI = jsonPath.getString("firstName")+" "+jsonPath.getString("lastName");
        String roleFromAPI = jsonPath.getString("role");

        String[] restOfTheAPIinfo = BookItApiUtils.getMyInfo(ApiStepDefs.emailGlobal,ApiStepDefs.passwordGlobal);

        String teamFromAPI = restOfTheAPIinfo[0];
        String batchFromAPI = restOfTheAPIinfo[1];
        String campusFromAPI = restOfTheAPIinfo[2];

        System.out.println("batchFromAPI = " + batchFromAPI);
    }


    @Then("All five information from three environment should match")
    public void all_five_information_from_three_environment_should_match() {




    }


}

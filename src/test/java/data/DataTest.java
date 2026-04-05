package data;

import org.testng.annotations.DataProvider;

public class DataTest {

    public static Integer spaceId;
    public static Integer boardId;


   @DataProvider(name = "spaceData")

    public static Object [] [] spaceData() {
       return new Object [] [] {
               {"OpenSpace",1},
               {"OpenSpace",2}
       };
   }

}

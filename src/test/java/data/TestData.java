package data;

import org.testng.annotations.DataProvider;

import static model.Driver.*;


public class TestData {


    @DataProvider(name = "driverData")
    public static Object[][] getDriverList() {
        return new Object[][]{{CHROME}, {EDGE}, {FIREFOX}};
    }

    public static Object[][] getJobList() {
        return new Object[][]{{"Istanbul, Turkey", "Quality Assurance"}};
    }

    @DataProvider(name = "driverAndJobListData")
    public static Object[][] driverAndJobListData() {
        return mergeData(getDriverList(), getJobList());
    }

    private static Object[][] mergeData(Object[][] list1, Object[][] list2) {
        int d1Size = list1.length * list2.length;
        int d2Size = list1[0].length + list2[0].length;

        Object[][] ret1 = new Object[d1Size][d2Size];
        int d1Index = 0;
        int d2Index = 0;
        for (Object[] item1 : list1) {
            for (Object[] item2 : list2) {
                for (Object o1 : item1) {
                    ret1[d1Index][d2Index] = o1;
                    d2Index++;
                }
                for (Object o2 : item2) {
                    ret1[d1Index][d2Index] = o2;
                    d2Index++;
                }
                d1Index++;
                d2Index = 0;
            }
        }
        return ret1;
    }


}

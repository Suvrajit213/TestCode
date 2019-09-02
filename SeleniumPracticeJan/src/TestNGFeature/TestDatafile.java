package TestNGFeature;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestDatafile {

	@DataProvider(name = "testdata")

	public static Object[][] testdata(Method m) {

		Object[][] obj = null;

		if (m.getName().equals("registeruser")) {

			obj = new Object[3][4];

			obj[0][0] = "Rahul";
			obj[0][1] = "Sharma";
			obj[0][2] = "r1";
			obj[0][3] = "s1";

			obj[1][0] = "Rahul2";
			obj[1][1] = "Sharma2";
			obj[1][2] = "r2";
			obj[1][3] = "s2";

			obj[2][0] = "Rahul3";
			obj[2][1] = "Sharma3";
			obj[2][2] = "r3";
			obj[2][3] = "s3";
			return obj;
		}

		if (m.getName().equals("registeruser1")) {

			obj = new Object[3][2];
			obj[0][0] = "Rahul";
			obj[0][1] = "Sharma";

			obj[1][0] = "Rahul2";
			obj[1][1] = "Sharma2";

			obj[2][0] = "Rahul3";
			obj[2][1] = "Sharma3";
		}

		if (m.getName().equals("loginuser")) {

			obj = new Object[3][2];

			obj[0][0] = "r1";
			obj[0][1] = "s1";

			obj[1][0] = "r2";
			obj[1][1] = "s2";

			obj[2][0] = "r3";
			obj[2][1] = "s3";

			return obj;

		}
		return obj;

	}

}

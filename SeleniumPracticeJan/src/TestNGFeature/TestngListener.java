package TestNGFeature;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestngListener implements ITestListener {

	@Override
	public void onFinish(ITestContext Result) {
		// TODO Auto-generated method stub

		System.out.println("The name of the testCase finish is:" + Result.getName());

	}

	@Override
	public void onStart(ITestContext Result) {
		// TODO Auto-generated method stub

		System.out.println("The name of the testCase Start is:" + Result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		// TODO Auto-generated method stub

		System.out.println("The name of the testCase Success Percentage is:" + Result.getName());

	}

	@Override
	public void onTestFailure(ITestResult Result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testCase Failure is:" + Result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult Result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testCase Skipped is:" + Result.getName());
	}

	@Override
	public void onTestStart(ITestResult Result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testCase Start is:" + Result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult Result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testCase Success is:" + Result.getName());
	}

}

package com.whaless.common;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}

	/**
	 * 生成dimens value文件
	 */
	@Test
	public void test() {
		int x = 3;
		for (int i = 1; i <= 1000; i++) {
			System.out.println(String.format("<dimen name=\"size_%d\">%ddp</dimen>", i, i * x));
		}
		System.out.println();
		for (int i = 1; i <= 500; i++) {
			System.out.println(String.format("<dimen name=\"text_%d_%d\">%ssp</dimen>", i / 10, i % 10, (float)i * x / 10));
		}
		System.out.println();
		for (int i = 1; i <= 100; i++) {
			System.out.println(String.format("<dimen name=\"size_%d_minus\">%ddp</dimen>", i, -i * x));
		}
	}

	/**
	 * 移动生成的apk release包
	 */
	@Test
	public void moveApk() {
		String versionName = "3.0.3";

		File savePath = new File("E:\\WorkSpace\\Android\\projects\\Tracing-2\\app-ui\\build\\apks");
		savePath.delete();
		savePath.mkdir();
		File file = new File("E:\\WorkSpace\\Android\\projects\\Tracing-2\\app-ui/build/outputs/apk");
		System.out.println("Apk parent Path is " + file.getAbsolutePath());
		if (file.exists()) {
			for (File item : file.listFiles()) {
				System.out.println("check " + item.getName());

				for (File anItems_ : item.listFiles()) {
					if (anItems_.getName().equals("release")) {
						System.out.println("check " + item.getName() + " release");
						for (File apk : anItems_.listFiles()) {
							if (apk.getName().contains(versionName)) {
								try {
									File saveFile = new File(savePath.getAbsoluteFile(), apk.getName());
									System.out.println("start copy " + apk.getAbsolutePath() + " to " + saveFile.getAbsolutePath());
//									FileUtils.copyFile(apk, saveFile);
									copyFile(apk, saveFile);
									System.out.println("move " + apk.getAbsolutePath() + " success!");
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 复制文件
	 */
	public void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream ins = new FileInputStream(fromFile);
		FileOutputStream out = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = ins.read(b)) != -1) {
			out.write(b, 0, n);
		}

		ins.close();
		out.close();
	}

	@Test
	public void createAccount(){
		Random random = new Random();
		for(int i = 0 ; i < 900; i ++ ){
			int port = i + 13004;
			StringBuilder pwd = new StringBuilder();
			for(int j = 0; j < 6; j ++){
				String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
				//输出字母还是数字
				if( "char".equalsIgnoreCase(charOrNum) ) {
					//输出是大写字母还是小写字母 65|97
					pwd.append((char) (random.nextInt(26) + 65));
				} else if( "num".equalsIgnoreCase(charOrNum) ) {
					pwd.append(String.valueOf(random.nextInt(10)));
				}
			}
			System.out.println(String.format("\"%s\":\"%s\",", String.valueOf(port), pwd));
		}
	}

	@Test
	public void testNum(){
		int count = 10500;
		DecimalFormat format = new DecimalFormat("#.0");
		format.setRoundingMode(RoundingMode.HALF_UP);
		System.out.print((float) count / 10000);
		System.out.print(format.format(new BigDecimal(String.valueOf((float) count / 10000))));
	}
}
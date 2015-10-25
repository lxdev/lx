package net.lx.common.vcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * 
 * @author 
 * 
 */
public class RandImgCreater {

	// ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789
	// 的一是在不了有和人这中大为上个国我以要他时来用们生到作地于出就分对成会可主发年动同工也能下过子说产种面而方后多定行学法所民得经十三之进着等部度家电力里如水化高自二理起小物现实加量都两体制机当使点从业本去把性好应开它合还因由其些然前外天政四日那社义事平形相全表间样与关各重新线内数正心反你明看原又么利比或但质气第向道命此变条只没结解问意建月公无系军很情者最立代想已通并提直题党程展五果料象员革位入常文总次品式活设及管特件长求老头基资边流路级少图山统接知较将组见计别她手角期根论运农指几九区强放决西被干做必战先回则任取据处队南给色光门即保治北造百规热领七海口东导器压志世金增争济阶油思术极交受联什认六共权收证改清己美再采转更单风切打白教速花带安场身车例真务具万每目至达走积示议声报斗完类八离华名确才科张信马节话米整空元况今集温传土许步群广石记需段研界拉林律叫且究观越织装影算低持音众书布复容儿须际商非验连断深难近矿千周委素技备半办青省列习响约支般史感劳便团往酸历市克何除消构府称太准精值号率族维划选标写存候毛亲快效斯院查江型眼王按格养易置派层片始却专状育厂京识适属圆包火住调满县局照参红细引听该铁价严
	private static final String CODE_LIST = "123456789123456789"; // 验证码取值范围
	private HttpServletResponse response = null;
	private static final int HEIGHT = 22; // 整个图片大小高度
	private static final int FONT_NUM = 4; // 验证码字数
	private int width = 10;
	private int iNum = 0;
	private String codeList = "";
	private boolean drawBgFlag = false;

	private int rBg = 0;
	private int gBg = 0;
	private int bBg = 0;

	// 图片最外层（控制显示大小）
	public RandImgCreater(HttpServletResponse response) {
		this.response = response;
		this.width = 13 * FONT_NUM + 20; // 整个图片大小宽度
		this.iNum = FONT_NUM;
		this.codeList = CODE_LIST;
	}

	public RandImgCreater(HttpServletResponse response, int iNum,
			String codeList) {
		this.response = response;
		this.width = 13 * iNum + 12;
		this.iNum = iNum;
		this.codeList = codeList;
	}

	public String createRandImage() {
		BufferedImage image = new BufferedImage(width, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		if (drawBgFlag) {
			g.setColor(new Color(rBg, gBg, bBg));
			g.fillRect(0, 0, width, HEIGHT); // 北京颜色层距离左上角位置及层大小
		} else {
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, HEIGHT);

			// 生成背景带线条
			for (int i = 0; i < 155; i++) {
				g.setColor(getRandColor(140, 200));
				int x = random.nextInt(width);
				int y = random.nextInt(HEIGHT);
				int xl = random.nextInt(15); // 背景图片线条
				int yl = random.nextInt(8); // 背景图片线条
				g.drawLine(x, y, x + xl, y + yl);
			}
		}

		g.setFont(new Font("", Font.BOLD, 24)); // 字体大小

		String sRand = "";
		for (int i = 0; i < iNum; i++) {
			int rand = random.nextInt(codeList.length());
			String strRand = codeList.substring(rand, rand + 1);
			sRand += strRand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			int fontx = 17 * i + 2; // 字体间距
			int fonty = 20;
			g.drawString(strRand, fontx, fonty);
		}
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sRand;
	}

	public void setBgColor(int r, int g, int b) {
		drawBgFlag = true;
		this.rBg = r;
		this.gBg = g;
		this.bBg = b;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}

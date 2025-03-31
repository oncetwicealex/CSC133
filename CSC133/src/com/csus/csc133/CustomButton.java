package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class CustomButton extends Button {

	public CustomButton(String text) {
		super(text);
		initCustom();
	}

	private void initCustom() {
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(0, 0, 0)));
		getAllStyles().setBgColor(ColorUtil.rgb(57, 89, 184));
		getAllStyles().setBgTransparency(255);
		getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
	}
}

package org.loon.test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.loon.framework.jtts.Engine;
import org.loon.framework.jtts.JTTS;

/**
 * Copyright 2008 - 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loonframework
 * @author chenpeng
 * @email��ceponline@yahoo.com.cn
 * @version 0.1
 */
public class Main extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTTS jtts;

	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JComboBox jComboBox1;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JSeparator jSeparator1;

	private javax.swing.JSlider jSlider1;

	private javax.swing.JSlider jSlider2;

	private javax.swing.JTextArea jTextArea1;

	public Main() {

		jtts = Engine.getTTS();

		jSlider1 = new javax.swing.JSlider();
		jSlider2 = new javax.swing.JSlider();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();

		this.setLayout(null);

		jSlider1.setName("jSlider1");
		jSlider1.setMaximum(500);
		jSlider1.setValue(150);
		jSlider1.setMinimum(0);
		this.add(jSlider1);
		jSlider1.setBounds(94, 131, 316, 24);

		jSlider2.setName("jSlider2");
		jSlider2.setMaximum(200);
		jSlider2.setValue(100);
		jSlider2.setMinimum(0);
		this.add(jSlider2);
		jSlider2.setBounds(94, 170, 316, 24);

		String[] types = new String[12];
		for (int i = 0; i < types.length; i++) {
			if (i < 5) {
				types[i] = "zh+" + "m" + (i + 1);
			} else {
				types[i] = "zh+" + "f" + (i - 4);
			}
		}
		types[10] = "zh+whisper";
		types[11] = "zh+croak";

		jtts.setLanguage("zh");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(types));
		jComboBox1.setName("jComboBox1");
		this.add(jComboBox1);
		jComboBox1.setBounds(100, 210, 100, 23);
		jComboBox1.addActionListener(this);

		jLabel1.setText("朗读间隔:");
		jLabel1.setName("jLabel1");
		this.add(jLabel1);
		jLabel1.setBounds(20, 125, 66, 30);

		jLabel2.setText("朗读音量:");
		jLabel2.setName("jLabel2");
		this.add(jLabel2);
		jLabel2.setBounds(20, 165, 70, 30);

		jLabel3.setText("发音规则:");
		jLabel3.setName("jLabel3");
		this.add(jLabel3);
		jLabel3.setBounds(20, 216, 80, 15);

		jLabel4.setText("目标文本:");
		jLabel4.setName("jLabel4");
		this.add(jLabel4);
		jLabel4.setBounds(20, 10, 70, 20);

		jSeparator1.setName("jSeparator1");
		this.add(jSeparator1);
		jSeparator1.setBounds(10, 268, 398, 10);

		jButton1.setText("朗读文本");
		jButton1.setName("jButton2");
		this.add(jButton1);
		jButton1.setBounds(232, 284, 83, 25);
		jButton1.addActionListener(this);

		jButton2.setText("退出应用");
		jButton2.setName("jButton3");
		this.add(jButton2);
		jButton2.setBounds(325, 284, 83, 25);
		jButton2.addActionListener(this);

		jScrollPane1.setName("jScrollPane1");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setName("jTextArea1");
		jTextArea1
				.setText("Hello Java！Hello World！\n前所未有啊，中国踢韩国三比零啦！\n生意兴隆，财源广进，万事如意，虎年大吉！");
		jScrollPane1.setViewportView(jTextArea1);

		this.add(jScrollPane1);
		jScrollPane1.setBounds(98, 10, 310, 106);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			if (source == jButton1) {
				jtts.setRate(jSlider1.getValue());
				jtts.setVolume(jSlider2.getValue());
				// 设定朗读文本
				jtts.speak(jTextArea1.getText().trim());
			}
			if (source == jButton2) {
				System.exit(0);
			}
		} else if (source instanceof JComboBox) {
			// 设定朗读语言
			jtts.setLanguage(jComboBox1.getSelectedItem().toString().trim());
		}
	}

	public static void createGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}
		JFrame frame = new JFrame("Java版TTS组件发音测试(eSpeak封装)");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Main());
		frame.pack();
		frame.setSize(new Dimension(420, 345));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});
	}

}

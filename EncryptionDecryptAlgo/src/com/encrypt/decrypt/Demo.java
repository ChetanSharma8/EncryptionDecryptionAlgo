package com.encrypt.decrypt;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Demo extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2924665604246536872L;
	char encrypted_mess[] = new char[11];
	char[] original_message = new char[10];

	static TextField t, t1, t2, t3;

	// JFrame
	static JFrame f, f1, f2, f3;

	// JButton
	static JButton b, b1, clickButton;

	// label to display text
	static JLabel l1, l2, l3, l5;

	static JTextPane l, l4;

	static JPanel p;

	private String radioButtonFlag = "Encryption";

	static CheckboxGroup cbg;

	static Checkbox checkBox1, checkBox2;

	static ButtonGroup bg;

	public String decryptText;
	public String simpleText;

	Demo() {
		
	}

	public static void main(String[] args) {

		l = new JTextPane();
		l.setContentType("text/html");
		l.setEditable(false);
		l.setBackground(null);
		l.setBorder(null);

		f = new JFrame("Encryption & Decryption Algorithm");
		f1 = new JFrame("Encryption & Decryption Algorithm");

		l = new JTextPane();
		l4 = new JTextPane();

		b = new JButton("Encrypt");
		b1 = new JButton("Decrypt");
		clickButton = new JButton("Click");

		Demo te = new Demo();

		b.addActionListener(te);
		b1.addActionListener(te);
		clickButton.addActionListener(te);

		t = new TextField(20);
		
		
		 t.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	                String text = t.getText();
	                String filteredText = text.replaceAll("[^a-zA-Z]", "");
					t.setText(filteredText);
	                if (text.length() < 10) {
	                    // Display an error message
	                	t.setText(text.substring(0, (text.length())));
	                	JOptionPane.showMessageDialog(te, "Text must be exactly 10 characters.", "Message",
								JOptionPane.ERROR_MESSAGE);
	                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                   b.setEnabled(false);
	                }else if (text.length() > 10) {
	                    // Display an error message
	                	t.setText(text.substring(0, (text.length())));
	                	JOptionPane.showMessageDialog(te, "Text must be exactly 10 characters.", "Message",
								JOptionPane.ERROR_MESSAGE);
	                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                    b.setEnabled(false);
	                }else if(text.length()==10) {
	                	b.setEnabled(true);
	                }
	            }
		 });

		t1 = new TextField(20);
		t1.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				String text = t1.getText();
				String filteredText = text.replaceAll("[^a-zA-Z]", "");
				t1.setText(filteredText);
				if (text.length() > 1) {
					t1.setText(text.substring(0, 1));
					JOptionPane.showMessageDialog(te, "Text must be 1 character", "Message", JOptionPane.ERROR_MESSAGE);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		t1.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				String text = t1.getText();
				String filteredText = text.replaceAll("[^a-zA-Z]", "");
				t1.setText(filteredText);
			}
		});

		t2 = new TextField(20);
		t2.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				String text = t2.getText();
				String filteredText = text.replaceAll("[^a-zA-Z]", "");
				t2.setText(filteredText);
				if (text.length() > 20) {
					t1.setText(text.substring(0, 20));
					JOptionPane.showMessageDialog(te, "Text must be 20 characters or less", "Message",
							JOptionPane.ERROR_MESSAGE);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});

		p = new JPanel(new FlowLayout());
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		final Label label = new Label();
		label.setAlignment(Label.CENTER);
		label.setSize(600, 300);

		cbg = new CheckboxGroup();
		checkBox1 = new Checkbox("Encryption", cbg, true);
		checkBox1.setBounds(100, 100, 50, 50);
		checkBox2 = new Checkbox("Decryption", cbg, false);
		checkBox2.setBounds(100, 150, 50, 50);
		p.setBorder(new CompoundBorder(new EmptyBorder(50, 50, 50, 50), new LineBorder(Color.BLACK)));
		p.add(checkBox1, gbc);
		p.add(checkBox2, gbc);
		p.add(label, gbc);

		checkBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Demo d1 = new Demo();
				d1.radioButtonFlag = "Encryption";
				d1.displayRadio(gbc, d1.radioButtonFlag);
			}
		});

		checkBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Demo d1 = new Demo();
				d1.radioButtonFlag = "Decryption";
				d1.displayRadio(gbc, d1.radioButtonFlag);
			}
		});
		l3 = new JLabel("Encrypted Text:");
		l5 = new JLabel("");
		te.displayRadio(gbc, te.radioButtonFlag);
	}

	@SuppressWarnings("deprecation")
	public void displayRadio(GridBagConstraints gbc, String value) {

		if (value.equals("Encryption")) {
			p.remove(l3);
			p.revalidate();
			p.repaint();
			p.remove(t2);
			p.revalidate();
			p.repaint();
			p.remove(b1);
			p.revalidate();
			p.repaint();
			p.remove(l4);
			p.revalidate();
			p.repaint();
			p.remove(l5);
			p.revalidate();
			p.repaint();
			t.setText("");
			t1.setText("");
			l.setText("");
			l1 = new JLabel("Enter Text:");
			p.add(l1, gbc);
			p.add(t, gbc);
			l2 = new JLabel("Enter Random Cover Text:");
			p.add(l2, gbc);
			p.add(t1, gbc);
			p.add(b, gbc);
			p.add(l, gbc);
			f.add(p);
			f.pack();
			f.setSize(500, 400);
//			f.show();
			f.setVisible(true);
		} else if (value.equals("Decryption")) {
			this.simpleText = t.getText();
			p.remove(t);
			p.revalidate();
			p.repaint();
			p.remove(t1);
			p.revalidate();
			p.repaint();
			this.decryptText = l.getText();
			p.remove(l);
			p.revalidate();
			p.repaint();
			p.remove(l1);
			p.revalidate();
			p.repaint();
			p.remove(l2);
			p.revalidate();
			p.repaint();
			p.remove(b);
			p.revalidate();
			p.repaint();
			if (this.decryptText.length() > 0) {
				t2.setText(this.decryptText.substring(16));
			} else {
				t2.setText("");
			}
			l4.setText("");
			l5 = new JLabel("Simple Text Was: " + this.simpleText);
			p.add(l5, gbc);
			p.add(l3, gbc);
			p.add(t2, gbc);
			p.add(b1, gbc);
			p.add(l4, gbc);
			f.add(p);
			f.pack();
			f.setSize(500, 400);
//			f.show();
			f.setVisible(true);
		}
	}

	public void encryptedMethod() {
		char cover_text[] = new char[2];
		char final_encrypted_mess[] = new char[11], fetched_cover_text[] = new char[1], original_mess[] = new char[10];
		int temp, sum = 0, sub = 0, mul = 0, div = 0, mod = 0, len_rct;
		int final_random_cover_text[], ascii_fetched_cover_text[];
		int k = 0;
		// int len = 0;
		int len_ct;
		int final_cover_text[] = new int[5];

		@SuppressWarnings("resource")

		String c = t.getText();
		System.out.println(c);

		int plain_text_length = c.length();
		System.out.println("\nlength of plain text is=");
		System.out.println(c.length());

		len_ct = (int) Math.floor((plain_text_length) / 6);
		System.out.println("\nlength of random cover text is= ");
		System.out.println(len_ct);
		System.out.println("\nEnter Random cover text= ");
		for (int i = 0; i < len_ct; i++) {
			Scanner sc = new Scanner(System.in);
//			String v = t1.getText();
			String v = t1.getText();
			char d = v.charAt(0);
			System.out.println("Random---" + d);
			int asc = (int) (d);
			System.out.println("\n Ascii code of random cover text is = ");
			System.out.println(asc);
			int defaultValue = 65;
			
			int first = defaultValue / 10;
			int second = defaultValue % 10;

			sc.close();
			for (int h = 0; h < (5 * len_ct); h++) {
				for (int j = 0; j < len_ct; j++) {
					sum = (first + second) + 10;
					sub = (first - second) + 10;
					mul = (first * second) + 10;
					div = (first / second) + 10;
					mod = (6 % 5) + 10;
					final_cover_text[0] = sum;
					final_cover_text[1] = sub;
					final_cover_text[2] = mul;
					final_cover_text[3] = div;
					final_cover_text[4] = mod;
				}
			}
		}
		System.out.println("\n Final cover text= ");
		for (int i = 0; i < (5 * len_ct); i++) {
			// System.out.println("\n");
			System.out.println(final_cover_text[i]);
		}
		int fct_len = final_cover_text.length;
		System.out.println("\n length of final cover text is = ");
		System.out.println(fct_len);
		System.out.println("\n ASCII code of plain text is= ");
		for (int i = 0; i < plain_text_length; i++) {
			String g = c;
			char t = g.charAt(i);
			int asc_plain_text = (int) (t);
			System.out.println("  " + asc_plain_text);
		}
		System.out.println("\n Value in sum is = ");
		System.out.println(sum);
		System.out.println("\n Value in sub is = ");
		System.out.println(sub);
		System.out.println("\n Value in mul is = ");
		System.out.println(mul);
		System.out.println("\n Value in div is = ");
		System.out.println(div);
		System.out.println("\n Value in mod is = ");
		System.out.println(mod);

		for (int i = 0; i < (5 * len_ct); i++) {
			for (int j = 0; j < fct_len; j++) {

				if (j == 0) {
					int t = 0;
					char[] r = { t1.getText().charAt(0) };
					encrypted_mess[t] =  r[0];
					t = t + 1;
					for (int e = 0; e < (plain_text_length / 2); e++) {
						encrypted_mess[t] = (char) (sum ^ (int) (c.toCharArray()[e]));
						t = t + 1;
						if ((plain_text_length - e - 1) >= plain_text_length / 2) {
							encrypted_mess[t] = (char) (sum ^ (int) (c.toCharArray()[plain_text_length - e - 1]));
							t = t + 1;
						}
					}
				}

				else if (j == 1) {
					int t = 3;
					for (int e = 1; e < (plain_text_length / 2); e++) {
						encrypted_mess[t] = (char) (sub ^ (int) (c.toCharArray()[e]));
						t = t + 1;
						if ((plain_text_length - e - 1) >= plain_text_length / 2) {
							encrypted_mess[t] = (char) (sub ^ (int) (c.toCharArray()[plain_text_length - e - 1]));
							t = t + 1;
						}
					}
				} else if (j == 2) {
					int t = 5;
					for (int e = 2; e < (plain_text_length / 2); e++) {
						encrypted_mess[t] = (char) (mul ^ (int) (c.toCharArray()[e]));
						t = t + 1;
						if ((plain_text_length - e - 1) >= plain_text_length / 2) {
							encrypted_mess[t] = (char) (mul ^ (int) (c.toCharArray()[plain_text_length - e - 1]));
							t = t + 1;
						}
					}
				}

				else if (j == 3) {
					int t = 7;
					for (int e = 3; e < (plain_text_length / 2); e++) {
						encrypted_mess[t] = (char) (div ^ (int) (c.toCharArray()[e]));
						t = t + 1;
						if ((plain_text_length - e - 1) >= plain_text_length / 2) {
							encrypted_mess[t] = (char) (div ^ (int) (c.toCharArray()[plain_text_length - e - 1]));
							t = t + 1;
						}
					}
				} else {
					int t = 9;
					for (int e = 4; e < (plain_text_length / 2); e++) {
						encrypted_mess[t] = (char) (mod ^ (int) (c.toCharArray()[e]));
						t = t + 1;
						if ((plain_text_length - e - 1) >= plain_text_length / 2) {
							encrypted_mess[t] = (char) (mod ^ (int) (c.toCharArray()[plain_text_length - e - 1]));
							t = t + 1;
						}
					}
				}
			}
		}
		System.out.println("\n After xor final encrypted message/data/text in Ascii code is= ");
		for (int d = 0; d < encrypted_mess.length; d++) {
			System.out.println((int) encrypted_mess[d]);
		}
		System.out.println("\n Final encrypted message/data/text is= ");
		for (int g = 0; g < encrypted_mess.length; g++) {
			System.out.println(encrypted_mess[g]);
		}
	}

	public void decryptionMethod() {
		char msg[], cover_text[], final_encrypted_mess[] = new char[11], fetched_cover_text[] = new char[2],
				encrypted_mess[] = new char[11];

		int len = 0, len_ct, temp, sum = 0, sub = 0, mul = 0, div = 0, mod = 0, len_rct = 1;
		int final_cover_text[], final_random_cover_text[] = new int[5], ascii_fetched_cover_text[] = new int[2];
		int k = 0, j = 0;
		System.out.println("\n Decryption Algorithm starts......");
		char encypted_mess[];
		System.out.println("\n Encrypted_message is = ");
		String c = t2.getText().substring(1);
		String f = t2.getText().substring(1);
//		char d = t2.getText().substring(0, 1).charAt(0);
		char d = 'A';
		System.out.println(c);
		int len_encrypted_mess = c.length();
		System.out.println("\n Length of encrypted message is = ");
		System.out.println(len_encrypted_mess);
		int olen = (int) (len_encrypted_mess - Math.ceil(len_encrypted_mess / 11));
		System.out.println("\n Original message length is = ");
		System.out.println(olen);
		int r = 0;
		char u = d;
		fetched_cover_text[r] = u;
		System.out.println("\n Fetched random cover text is= ");
		System.out.println(fetched_cover_text[r]);
		int s = 0;
		ascii_fetched_cover_text[s] = (int) (fetched_cover_text[r]);
		System.out.println("\n Fetched random cover text in ASCII code is= ");
		System.out.println(ascii_fetched_cover_text[s]);

		int asc = (int) (d);
		System.out.println("\n Ascii code of random cover text is = ");
		System.out.println(asc);
		int first = asc / 10;
		int second = asc % 10;

		System.out.println("First----" + first);
		System.out.println("Second----" + second);

		for (int i = 0; i < (5 * len_rct); i++) {
			for (int q = 0; q < len_rct; q++) {
				// temp= fetched_cover_text[j];
				sum = (first + second) + 10;
				sub = (first - second) + 10;
				mul = (first * second) + 10;
				div = (first / second) + 10;
				mod = (first % second) + 10;
				final_random_cover_text[0] = sum;
				final_random_cover_text[1] = sub;
				final_random_cover_text[2] = mul;
				final_random_cover_text[3] = div;
				final_random_cover_text[4] = mod;
			}
		}
		System.out.println("\nFinal random cover text= ");
		for (int i = 0; i < (5 * len_rct); i++) {
			System.out.println("  " + final_random_cover_text[i]);
		}
		int len_frct = final_random_cover_text.length;
		System.out.println("\n Length of final random cover text is = " + len_frct);

		for (int i = 1; i < len_encrypted_mess; i++) {
			int n = 0;
			final_encrypted_mess[n] = encrypted_mess[i];
			n = n + 1;
			// char l = (char) final_encrypted_mess[n];
		}
		System.out.println("\n final encrypted message is = " + "   " + f);
		String g = f.toString();
		char t = g.charAt(0);
		int len_final_encrypted_mess = g.length();
		System.out
				.println("\n Length of final encrypted message or final encipher text is= " + len_final_encrypted_mess);
		System.out.println("\n ASCII code of final encrypted message or final encipher text is= ");
		for (int i = 0; i < len_final_encrypted_mess; i++) {
			String h = f.toString();
			char q = h.charAt(i);
			int ascii_final_encrypted_message = (int) (q);
			System.out.println("  " + ascii_final_encrypted_message);
		}
		for (int i = 0; i < (5 * (len_rct)); i++) {
			// cout<<"\n Length of final random cover text is = "<<len_frct;
			for (int l = 0; l < olen; l++) {
				for (int v = 0; v < len_frct; v++) {
					if (v == 0) {
						int y = 0;
						for (int p = 0; p < (len_final_encrypted_mess / 2); p++) {
							original_message[y] = (char) (sum ^ (int) f.toCharArray()[p]);
							y = y + 1;
							p = p + 1;
							if ((olen - y) > y) {
								original_message[olen - y] = (char) (sum ^ (int) (f.toCharArray()[p]));
								y = y + 1;
							}
						}
					} else if (v == 1) {
						int y = 1;
						for (int q = 2; q < (len_final_encrypted_mess / 2); q++) {
							original_message[y] = (char) (sub ^ (int) (f.toCharArray()[q]));
							y = y + 1;
							q = q + 1;
							if ((olen - y) > y) {
								original_message[olen - y] = (char) (sub ^ (int) (f.toCharArray()[q]));
								y = y + 1;
							}
						}
					} else if (v == 2) {
						int y = 2;
						for (int x = 4; x < (len_final_encrypted_mess / 2); x++) {
							original_message[y] = (char) (mul ^ (int) (f.toCharArray()[x]));
							y = y + 1;
							x = x + 1;
							if ((olen - y) > y) {
								original_message[olen - y] = (char) (mul ^ (int) (f.toCharArray()[x]));
								y = y + 1;
							}
						}
					} else if (v == 3) {
						int y = 3;
						// i=i+1;
						for (int a = 6; a < (len_final_encrypted_mess); a++) {
							original_message[y] = (char) (div ^ (int) (f.toCharArray()[a]));
							y = y + 1;
							a = a + 1;
							if ((olen - y) > y) {
								original_message[olen - y] = (char) (div ^ (int) (f.toCharArray()[a]));
								y = y + 1;
							}
						}
					} else {
						int y = 4;
						// i=i+1;
						for (int b = 8; b < (len_final_encrypted_mess); b++) {
							original_message[y] = (char) (mod ^ (int) (f.toCharArray()[b]));
							y = y + 1;
							b = b + 1;
							if ((olen - y) >= y) {
								original_message[olen - y] = (char) (mod ^ (int) (f.toCharArray()[b]));
								y = y + 1;
							}
						}
					}
				}
			}
		}
		int len_original_message = original_message.length;
		System.out.println("\n Length of original message is = " + len_original_message);
		System.out.println("\n After xor final original message/data/text is= ");
		for (int i = 0; i < len_original_message; i++) {
			System.out.println("  " + (int) (original_message[i]));
		}
		System.out.println("\n Final original message/data/text is= ");
		for (int i = 0; i < len_original_message; i++) {
			System.out.println("  " + original_message[i]);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		if (s.equals("Encrypt")) {
			this.encryptedMethod();
			String s1 = "";

			for (int g = 0; g < encrypted_mess.length; g++) {
				s1 = s1 + encrypted_mess[g];
				System.out.println(encrypted_mess[g]);
			}
			l.setText("Encrypted Text: " + s1);
		} else if (s.equals("Decrypt")) {
			this.decryptionMethod();
			int len_original_message = original_message.length;
			String s1 = "";

			System.out.println("\n Final original message/data/text is= ");
			for (int i = 0; i < len_original_message; i++) {
				s1 = s1 + original_message[i];
				System.out.println("  " + original_message[i]);
			}
			l4.setText("Decrypted Text: " + s1);
		}
	}

}

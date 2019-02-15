import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Interface extends JFrame implements ActionListener{
	
	JPanel p = new JPanel();
	JLabel leb = new JLabel("Donnez l'emplacement du benchmark : ");
	JTextField eb = new JTextField();
	JLabel ln1 = new JLabel("noeud 1");
	JLabel llien = new JLabel("lien");
	JLabel ln2 = new JLabel("noeud 2");
	JTextField n1 = new JTextField();
	JTextField lien = new JTextField();
	JTextField n2 = new JTextField();
	JButton prop1 = new JButton("Propagation 1");
	JButton prop2 = new JButton("Propagation 2");
	JButton herit = new JButton("Heritages");
	JTextArea rep = new JTextArea();
	JScrollPane jsp=new JScrollPane(rep);
	
	public Interface()
	{
		this.setTitle("TP RC");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p.setLayout(null);
		
		leb.setBounds(75, 100, 225, 25);
		eb.setBounds(75, 125, 450, 25);
		ln1.setBounds(75, 175, 50, 25);
		n1.setBounds(130, 175, 75, 25);
		llien.setBounds(220, 175, 50, 25);
		lien.setBounds(255, 175, 75, 25);
		ln2.setBounds(345, 175, 50, 25);
		n2.setBounds(400, 175, 75, 25);
		prop1.setBounds(75,225,175,25);
		prop1.addActionListener(this);
		prop2.setBounds(275,225,175,25);
		prop2.addActionListener(this);
		herit.setBounds(475,225,175,25);
		herit.addActionListener(this);
		rep.setBounds(75, 265, 450, 125);
		rep.setEditable(false);
		
		p.add(leb);
		p.add(eb);
		p.add(ln1);
		p.add(n1);
		p.add(llien);
		p.add(lien);
		p.add(ln2);
		p.add(n2);
		p.add(prop1);
		p.add(prop2);
		p.add(herit);
		p.add(rep);
		
		this.setContentPane(p);
		this.setVisible(true);		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==prop1)
		{
			String[][] data = null;
			try {
				data=read_bench(eb.getText());
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			ResSem rs = new ResSem(data);
			rep.setText(rs.propagation1(n1.getText(), lien.getText(), n2.getText()));
		}
		
		if(e.getSource()==prop2)
		{
			String[][] data = null;
			try {
				data=read_bench(eb.getText());
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			ResSem rs = new ResSem(data);
			rep.setText(rs.propagation2(n1.getText(), lien.getText(), n2.getText()));
		}
		
		if(e.getSource()==herit)
		{
			
		}
		
	}
	
	public String[][] read_bench (String bench) throws IOException
	{
		File file = new File(bench);
		BufferedReader in = new BufferedReader(new FileReader(file));
		String txt;
		int i=-1;
		String[][] data = null; 
		while((txt=in.readLine()) != null)
		{
			if (i==-1)
			{
				data=new String[Integer.parseInt(txt)+1][Integer.parseInt(txt)+1];
				i++;
			}
			else
			{
				data[i]=txt.split(" ");
				i++;
			}
		}
		return data;
	}

}

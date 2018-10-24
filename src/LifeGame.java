import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LifeGame implements ActionListener {
	
	private
	WorldMap wm;
    JButton bt1;      //��ʼ
    JButton bt2;      //��ͣ�ͼ���

	 public static void main(String []args) {
	    	new LifeGame();
	    }
	
	//��ʼ������
      LifeGame() {
    	JFrame jf = new JFrame("������Ϸ");
    	jf.setLayout(null);    	
    	Font ft = new Font("����",Font.BOLD,10);
    	
    	bt1 = new JButton("��ʼ");
        bt2 = new JButton("��ͣ");
        
        bt1.setFont(ft);
        bt2.setFont(ft);
        
    	bt1.setBounds(50, 50, 80, 80);
    	bt2.setBounds(250, 50, 80, 80);
    	
    	wm = new WorldMap();
    	wm.setBounds(50, 200, 300, 300);
    	
    	jf.add(bt1);
        jf.add(bt2);
    	jf.add(wm);
    	
    	jf.setBounds(400, 100, 500, 500);
   
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        
    	jf.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent arg0) {
    	
    	if(arg0.getSource()==bt1) {	
    	   wm.start1();
    	}
    	else if(arg0.getSource()==bt2) {
    		if(bt2.getText().equals("��ͣ")) {
    		wm.stop1();
    		bt2.setText("����");
    		}
    		else if(bt2.getText().equals("����")) {
            	wm.start1();
            	bt2.setText("��ͣ");
            }
    	}   	    	
    }
    	
}
	

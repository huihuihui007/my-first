import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.math.*;
public class WorldMap  extends JPanel  {
	private 
	int width =20;        //ÿ��ϸ���Ŀ��
    int height=20;        //ÿ��ϸ���ĸ߶�
    int wide_Map = 10;    //���
	int height_Map = 10;  //����
    // ����֮֡�����ʱ��ÿ��60֡  
    private final int DELAY_TIME = 1200;
    private Timer timer;
    double  rate =0.25;    //��ϸ���ĸ���
	//1�����ǻ�ϸ��,����Χ�Ŀհ׵�����ϸ��������            
	final int cell [][]= { {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
			               {0,0,0,0,0,0,0,0,0,0,0,0},
	};
	 
    int temp[][]=new int[wide_Map+2][height_Map+2];         //��ǰ״̬  
	int nextTemp[][] = new int[wide_Map+2][height_Map+2];   //��һ״̬
	
	WorldMap(){
	  initMap();
     startAction();
	}
	
	//�����ʼ��
	public void initMap() {
    for(int i=1;i<=wide_Map;i++) {
    	for(int j=1;j<=height_Map;j++) {
    		if(Math.random()<=rate)
    			temp[i][j]=1;
    		else
    			temp[i][j]=0;
			nextTemp[i][j]=cell[i][j];
    	}
    }
	}
	
	//������
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i =1; i <=wide_Map;i++) {
			for(int j=1;j<=height_Map;j++) {
				if(temp[i][j]==1)
					g.fillRect((i-1) * width, (j-1) * height, width, height);
				else
				g.drawRect((i-1) * width, (j-1) * height, width, height);
			}
		}
	}
	
	//��ȡ��ǰϸ����Χ��ϸ���ĸ���
	public int neighborCell(int x,int y) {
    int number = 0;
   for(int i = x-1 ;i<=x+1;i++) {
	   for(int j =y-1;j<=y+1;j++) {
		   if(temp[i][j]==1)
			   number++;
	   }
   }
   if(temp[x][y]==1)
	   number--;
	return number;
    }
	
	//�ı�ϸ��״̬
	public void changeState() {
	for(int i =1;i<=wide_Map;i++) {
		for(int j=1;j<=height_Map;j++) {
			switch(neighborCell(i,j)) {
			case 0: case 1: case 4: case 5:
			case 6: case 7: case 8:
				nextTemp[i][j]=0;break;
			case 2: nextTemp[i][j]=temp[i][j];break;
			case 3: nextTemp[i][j]=1;break;
			}
		}
	}
	copyMap();
	}
	
	
	//������һ��ͼ����ǰ��ͼ
	public void copyMap() {
		for(int i=0;i<=wide_Map+1;i++) {
			for(int j=0;j<=height_Map+1;j++) {
				temp[i][j]=nextTemp[i][j];
			}
		}
	}
	
	//���ö�ʱ��
	public void  startAction() {
		
	 timer = new Timer(DELAY_TIME,new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			changeState();
			repaint();
		}	
	});
	}
	
	//��ʼ
	public void start1() {
		timer.start();
	}
	
	//��ͣ
	public void stop1() {
		 timer.stop();
	}
	
}

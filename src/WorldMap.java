import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.math.*;
public class WorldMap  extends JPanel  {
	private 
	int width =20;        //每个细胞的宽度
    int height=20;        //每个细胞的高度
    int wide_Map = 10;    //宽度
	int height_Map = 10;  //长度
    // 动画帧之间的延时，每秒60帧  
    private final int DELAY_TIME = 1200;
    private Timer timer;
    double  rate =0.25;    //活细胞的概率
	//1代表是活细胞,把周围的空白当成死细胞来看待            
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
	 
    int temp[][]=new int[wide_Map+2][height_Map+2];         //当前状态  
	int nextTemp[][] = new int[wide_Map+2][height_Map+2];   //下一状态
	
	WorldMap(){
	  initMap();
     startAction();
	}
	
	//随机初始化
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
	
	//划矩形
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
	
	//获取当前细胞周围活细胞的个数
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
	
	//改变细胞状态
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
	
	
	//复制下一地图到当前地图
	public void copyMap() {
		for(int i=0;i<=wide_Map+1;i++) {
			for(int j=0;j<=height_Map+1;j++) {
				temp[i][j]=nextTemp[i][j];
			}
		}
	}
	
	//设置定时器
	public void  startAction() {
		
	 timer = new Timer(DELAY_TIME,new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			changeState();
			repaint();
		}	
	});
	}
	
	//开始
	public void start1() {
		timer.start();
	}
	
	//暂停
	public void stop1() {
		 timer.stop();
	}
	
}

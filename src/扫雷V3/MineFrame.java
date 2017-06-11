package ɨ��V3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * ɨ�׽���
 * @author hm
 *
 */
public class MineFrame extends JFrame implements Common{
	public static int combs=10;//������
	private int []mine=new int [40];//����λ��
	private JTextField jt=new JTextField();
	private Graphics g;
	
	//ʵ����һ���¼�������Ϊ����
    private MineListener ml=new MineListener();	
	private static MineFrame mf;
	//����������
	public static void main(String[] args) {
		mf=new MineFrame();
		mf.Sweepuint();
	
	}
	
	//�����������Ϊ����
		private	JPanel jp = new JPanel(){
				//��дpaint����(�ػ�)
				public void paint(Graphics g){
					super.paint(g);	
					//������
					for(int i=0;i<LIST;i++){
						g.drawLine(X, Y+SIZE*i,X+SIZE*(LIST-1),Y+SIZE*i );
					}//������
					for(int i=0;i<ROW;i++){
						g.drawLine(X+SIZE*i, Y, X+SIZE*i, Y+SIZE*(ROW-1));
					}
					//��������
					for(int i=0;i<9;i++)
					    for(int j=0;j<9;j++){
					    	//��ÿ�����񻭳�3D��ťЧ��
					        for(int k=0;k<5;k++){
			                     Color c=new Color(100+15*k,100+15*k,100+15*k);
			                     g.setColor(c);
			                     g.drawRect((X+SIZE*j)+k, (Y+SIZE*i)+k, SIZE-2*k, SIZE-2*k);
			                   
					        }
					}
					//�ػ���Ϸ����	
				   if(RL.size()>0){
					  for(int i=0;i<RL.size();i++){
						    //��ȡ��������е�ֵ
					        Mine m=(Mine)RL.get(i);					        
					        int r=m.getRow();
					        int l=m.getList();
					        //ʵ��������ͼƬ����
			    	        Picture p=new Picture(g);
			    	        //���û���ͼƬ�ķ���
			    	        p.drawPicture(r,l);	
					    }
					}
				   //����־
				   if(FlagList.size()>0){
						  for(int i=0;i<FlagList.size();i++){
							    //��ȡ��������е�ֵ
						        Mine m=(Mine)FlagList.get(i);					        
						        int r=m.getRow();
						        int l=m.getList();
						        //ʵ��������ͼƬ����
				    	        Picture p=new Picture(g);
				    	        //���û���ͼƬ�ķ���
				    	        p.drawFlag(r,l);	
						    }
						}
	
					
				}
				
			};
		
    //����ɨ�״���ķ���
	public void Sweepuint() {
		//���ô�������
		this.setTitle("ɨ��soldier");
		this.setSize(new Dimension(300,400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		//���ô����˵����ķ���
		this.menu();
		//���ô����������ķ���
		this.northPanel();
		//����м��������
		this.add(jp);
		//���������ɫ
		jp.setBackground(Color.LIGHT_GRAY);
		//��ʾ����
		this.setVisible(true);
		
		g=jp.getGraphics();	
		System.out.print("ȡ�û�����");
		
		this.setDefaultCloseOperation(3);
		System.out.println(" Main frame is visible !");
	
		ml.sendPanel(jp, g,mf);
		f.sendPanel(jp, g);
	}
	
	
	
	//�����������ķ���
	private void northPanel() {
		//ʵ����һ��������
		JPanel jp=new JPanel();	
		//�����������
		jp.setPreferredSize(new Dimension(0,50));
		jp.setBackground(Color.ORANGE);
		
		//jp.setLayout(null);
		//�������λ��
		this.add(jp,BorderLayout.NORTH);
		
		ImageIcon im =new ImageIcon("images/001.jpg");
		//ʵ����һ����ť����
		JButton jp1=new JButton(im);
		jp1.setPreferredSize(new Dimension(39,39));
		jp.add(jp1);
		//������ʾ������
		jt.setPreferredSize(new Dimension(50,40));
		jp.add(jt);
		
//		ImageIcon im2 =new ImageIcon("images/004.jpg");
//		//ʵ����һ����ť����
//		JButton jp2=new JButton(im2);	
//		jp2.setPreferredSize(new Dimension(39,39));
//		jp.add(jp2);
//		
//		//ʵ����һ����ʾ�����
//		JTextField jt1=new JTextField();	
//		jt1.setPreferredSize(new Dimension(50,40));
//		jp.add(jt1);
	}
	//�����˵����ķ���
	private void menu() {
		//ʵ����һ���˵�������
		JMenuBar jmb=new JMenuBar();
		String []array={"�¿�","���","����","���а�"};
		String [][]item={{"����","�м�","�߼�","����"},{"��ǿ�","��ǹ�"},{"˵��","��Ȩ"},{"�鿴"}};
		//��������
		for(int i=0;i<array.length;i++){
			//ʵ�����˵�ѡ�����
			JMenu jm=new JMenu(array[i]);
			jmb.add(jm);
			for(int j=0;j<item[i].length;j++){
			    //ʵ�����˵���ѡ�����
			    JMenuItem jmt=new JMenuItem(item[i][j]);
			    //��Ӽ���������
			    jmt.addActionListener(l);
			    jm.add(jmt);
			}	
		}
		this.setJMenuBar(jmb);
	}
	
	
	//��Բ˵����������ڲ���
	private ActionListener l=new ActionListener(){
		// MineFrame mf=new MineFrame();
		public void actionPerformed(ActionEvent e) {
			String s=e.getActionCommand();
			if(s.equals("����")){
				combs=10;
				System.out.println("=======>"+combs);
				newPlay(s);
			}else if(s.equals("�м�")){
				combs=15;
				System.out.println("=======>"+combs);
				newPlay(s);
			}else if(s.equals("�߼�")){
				combs=20;
				System.out.println("=======>"+combs);
				newPlay(s);
				
			}else if(s.equals("����")){
				combs=10;
				System.out.println("=======>"+combs);
				newPlay(s);
			}else if(s.equals("��ǿ�")&&f.getSign()==0){	
				//������ɫ���ο�
			    g.setColor(Color.GREEN);
			    for(int k=0;k<4;k++){
			        g.drawRect(X-k,Y-k ,X+(ROW-1)*SIZE,Y+(LIST-1)*SIZE-2*k);
			    }
			    System.out.println("ml remove and f add");
				jp.removeMouseListener(ml);				
				//�������Ӽ���������
				jp.addMouseListener(f);	
				f.setSign(1);
			}else if(s.equals("��ǹ�")&&f.getSign()==1){		
				 g.setColor(Color.LIGHT_GRAY);
				 for(int k=0;k<4;k++){
					 g.drawRect(X-k,Y-k ,X+(ROW-1)*SIZE,Y+(LIST-1)*SIZE-2*k);
				    }
				 System.out.println("ml add and f remove");
				 jp.removeMouseListener(f);				
				 //�������Ӽ���������
				 jp.addMouseListener(ml);
				 f.setSign(0);
			}

			else if(s.equals("ָ��")){
				JOptionPane.showMessageDialog(null, "��Ϸָ�ϣ�\n������¿����˵���ѡ����Ϸ���׼���,��ʼ��Ϸ\n" +
						"�������λ�ã�����ʾ��ʾͼƬ����ʾ����Χ8��λ�õ�������ֱ������ҳ������ף���ʤ��\n" +
						"�������־���˵���ѡ���Ƿ�����־�������������С�����־��¼�׵�λ�ã��رձ�־�������Ϸ");
				
			}else if(s.equals("��Ȩ")){
				JOptionPane.showMessageDialog(null,"��Ȩ���У�����");
				
			}else if(s.equals("�鿴")){
				
			}
						
		}
	
	};	
		//���¿��ֵķ���
		public void newPlay(String s){
			jt.setText(""+combs);
			//���һ�ζ�ά����
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++)
					MineArea[i][j]=0;
			//����������
			if(RL.size()>0){
			    for(int i=RL.size();i>=0;i--)
				    RL.remove(i);
			}
		   
			
			if(FlagList.size()>0){
			    for(int i=FlagList.size();i>=0;i--)
				    FlagList.remove(i);
			}
			 //��ʼ�����
			jp.paint(g);
			//�����׵�λ��
			produceMines();
		
			//���ݲ�������ȷ�������������������
			setData();
			
			//�������Ӽ���������
		    jp.addMouseListener(ml);
		    jp.addMouseMotionListener(ml);
            ml.setNew(s);
		   
		}
		
		//��ʼ���׵�λ�õķ���
		public void produceMines(){
			//���������ڵ�λ��,�������ظ��������
			Random ran=new Random();
			for(int i=0;i<combs;i++){
				Boolean flag=true;
			    int temp=ran.nextInt(81);   
		        for(int j=0;j<i;j++){     
			    	if(mine[j]==temp){
			    		flag=false ;	
			    		break;
			    	}  
		        }
		        if(flag){
		        	mine[i]=temp;
		        }
		        else 
			      i--;
			 }			
			for(int i=0;i<combs;i++){
				System.out.println(mine[i]);
			}
		}
		
		//��������Χ�����ֵķ���
		public void setData(){
			for(int i=0;i<combs;i++){
				//����ÿ�������ڵĺ�����λ��,�ö�ά����洢��λ��
			    int x=mine[i]/9;
			    int y=mine[i]-9*x;
			    MineArea[x][y]=100;		
			}		
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++){
					if(MineArea[i][j]==0){
						int count=0;
						if(i>0 && i<8 && j>0 && j<8){
							//������Χ8��λ�ý��м��
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;						
						}
						else if(i==0 && j==0){
							//��3��λ�ý��м��
							if(MineArea[i][j+1]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						else if(i==0 && j==8){
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						else if(i==8 && j==0){
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
						}
						else if(i==8 && j==8){
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i-1][j]==100)
								count++;
							
						}
						else if(i==0 && j>0 && j<8){
							//��5������λ�ý��м��
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
						}
						else if(i==8 && j>0 && j<8){
							//��5������λ�ý��м��
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
						}
						else if(j==0 && i>0 && i<8){
							//��5������λ�ý��м��
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j+1]==100)
								count++;
							if(MineArea[i][j+1]==100)
								count++;
							if(MineArea[i+1][j+1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						else if(j==8 && i>0 && i<8){
							//��5������λ�ý��м��
							if(MineArea[i-1][j]==100)
								count++;
							if(MineArea[i-1][j-1]==100)
								count++;
							if(MineArea[i][j-1]==100)
								count++;
							if(MineArea[i+1][j-1]==100)
								count++;
							if(MineArea[i+1][j]==100)
								count++;
						}
						System.out.println("array["+i+"]"+"["+j+"]="+count);
						MineArea[i][j]=count;
					}
						
				}
		}
		
		

	
	
	
	
	
	

}

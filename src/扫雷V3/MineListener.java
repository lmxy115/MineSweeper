package ɨ��V3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineListener extends MouseAdapter implements Common{
	private Graphics g;
    private JPanel jp;
    private int r;
    private int l;
    private String newCommand="";
    private int tr=0;
    private int tl=0;
    private MineFrame mf;
    
    public MineListener(){   	
    }
    public void setNew(String s){
	    newCommand=s;
    }
    
    public void sendPanel(JPanel jpl,Graphics g,MineFrame mf){
    	jp=jpl;
    	this.g=g;
    	this.mf=mf;
    	System.out.println(" here is get graphics from panel...");
    }
    
	public void mousePressed(MouseEvent e){
		int x=e.getX();		
	    int y=e.getY();
	   //��������
	   l=(x-X)/SIZE;
	   r=(y-Y)/SIZE;		
	   System.out.println("r="+r+"   l="+l);		
	}
	public void mouseReleased(MouseEvent e){
	
		int temp=MineArea[r][l];	
		System.out.println("temp="+temp);
		//�����λ���Ƿ����׵�λ��
		this.click(temp);
	    //����Ƿ�Ӯ��
		this.win();
			 	    										
	}	
    public void mouseMoved(MouseEvent e){
    	
    	if(f.getSign()==0){
    	    //��ȡ��ǰ�к���  	
    	   int x=e.getX();
    	   int y=e.getY();
    	   int ll=(x-X)/SIZE;
 	       int rr=(y-Y)/SIZE;
 	      // System.out.println("ll="+ll+"rr="+rr);
 	 
    	 //ʵ��������ͼƬ����
	        Picture p=new Picture(g);
    	    if(tr==rr && tl==ll  && ll<9 &&rr<9){    	   
    	    //���û���ͼƬ�ķ���
    	        p.drawSoldier(rr,ll);
    	    
    	   
    	    }else if(( tr!=rr || tl!=ll  )&& ll<9 &&rr<9){
    		//����һ��λ���Ƴ�
    		 p.remove(tr, tl);
    		//�ѵ�ǰλ�ø�Ϊ��һ��λ��ǰһλ��
    		tr=rr;
    	    tl=ll;
    	    //���Ƶ�ǰλ�õ�ͼƬ
    	    p.drawSoldier(rr,ll);
    		}
    	
    	}
    }
	
	//�ж�Ӯ�ķ���
	public void win(){
		System.out.println("RL.size="+RL.size());
		if(RL.size()==81-MineFrame.combs){
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++){
					if(MineArea[i][j]==100){						
						//ʵ��������ͼƬ����
				    	Picture p=new Picture(g);
				    	//���û���ͼƬ�ķ���
				    	p.drawFlag(i,j);					    	
				    	//��¼����ʾ����λ��
						Mine m=new Mine(i,j);
						RL.add(m);
						if(!FlagList.check(m))
						    FlagList.add(m);
					}
				}	
			if(newCommand.equals("����")){
				JOptionPane.showMessageDialog(null,"����ڶ��أ�����");
				MineFrame.combs=15;
				mf.newPlay("����");
		    	jp.removeMouseListener(this);	
				jp.removeMouseListener(f);
			}else {
	            	JOptionPane.showMessageDialog(null,"You are winner������");
			    	jp.removeMouseListener(this);	
					jp.removeMouseListener(f);
			}
			    
		}
			
	}
	
	//���ݵ����λ����ʾͼƬ�ķ���
	public void click(int t){
		if(t==100){
			
			System.out.println("������!!!!!!!");


			//��ձ�־����
			if(FlagList.size()>0){
		    for(int i=FlagList.size();i>=0;i--)
			    FlagList.remove(i);
		}	
						
			//��ʾ����ͼƬ����������λ�õ��������
			for(int i=0;i<9;i++)
			    for(int j=0;j<9;j++){
			    	Mine m=new Mine(i,j);
			    	//if(!RL.check(m)){
			    	System.out.println("i="+i+" j="+j);
						   RL.add(m);
						  //ʵ��������ͼƬ����
					      Picture p=new Picture(g);
					      //���û���ͼƬ�ķ���
					      p.drawPicture(i,j);	
						//}	
			}
			
			JOptionPane.showMessageDialog(null,"���ף�Game Over������");
			jp.removeMouseListener(this);
			jp.removeMouseListener(f);
		}
		
		//������Ĳ����׵�λ������ʾ����
		else if(t==1 ||t==2 ||t==3 ||t==4 ||t==5 ||t==6 ||t==7){
			System.out.println("����");						
			//��¼����ʾ����λ��
			Mine m=new Mine(r,l);
			System.out.println("Check:"+RL.check(m));
			if(!RL.check(m)){
			   RL.add(m);
			  //ʵ��������ͼƬ����
		      Picture p=new Picture(g);
		      //���û���ͼƬ�ķ���
		      p.drawPicture(r,l);	
			}
			
		}
		//�����λ��Ϊ�հ�
		else if(t==0){
			this.searchAround(r, l);		
		}
	}
	
	//����λ����Χ��8��λ�õ�����ķ���
	public void searchAround(int x,int y){
			System.out.println("====>search");
			Mine m=new Mine(x,y);
			//ʵ��������ͼƬ����
		    Picture p=new Picture(g);
		    //����λ��û�л���ͼƬ�򻭳�ͼƬ
	    	if(!RL.check(m)){
				   RL.add(m);				  
			      //���û���ͼƬ�ķ���
			      p.drawPicture(x,y);	
			}
	    	else if (RL.check(m))
	    		return;
		    //�Ը�λ�õ���Χ�����ж��Ƿ�Ϊ�գ�������ʾ�����8��λ�õ���Χ�Ƿ�Ϊ�գ��ݹ�	
			//���Ѿ���ʾͼƬ�򲻱���
			//���Ͻ�
		    if(x-1>-1 && x-1<9 && y-1>-1 && y-1<9 && MineArea[x-1][y-1]==0)				    
			    this.searchAround(x-1,y-1);    	
			else if(x-1>-1 && x-1<9 && y-1>-1 && y-1<9 &&MineArea[x-1][y-1]!=0 && MineArea[x-1][y-1]!=100){
				Mine m1=new Mine(x-1,y-1);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x-1,y-1);	
					}	
		
			}
		    //���Ϸ�	
		    if(x-1>-1 && x-1<9 && y>-1 && y<9 && MineArea[x-1][y]==0)
			    this.searchAround(x-1,y); 
			else if(x-1>-1 && x-1<9 && y>-1 && y<9 && MineArea[x-1][y]!=0 && MineArea[x-1][y]!=100){
				Mine m1=new Mine(x-1,y);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x-1,y);	
					}	
			}
            //���Ͻ�
		    if(x-1>-1 && x-1<9 && y+1>-1 && y+1<9 && MineArea[x-1][y+1]==0)
			    this.searchAround(x-1,y+1);
		    else if(x-1>-1 && x-1<9 && y+1>-1 && y+1<9 &&MineArea[x-1][y+1]!=0 && MineArea[x-1][y+1]!=100){
		    	Mine m1=new Mine(x-1,y+1);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x-1,y+1);	
					}	
		    }
		    //���ҷ�
		    if(x>-1 && x<9 && y+1>-1 && y+1<9 && MineArea[x][y+1]==0)
		    	this.searchAround(x,y+1);
		    else if(x>-1 && x<9 && y+1>-1 && y+1<9 && MineArea[x][y+1]!=0 && MineArea[x][y+1]!=100){
		    	Mine m1=new Mine(x,y+1);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x,y+1);	
					}	
		    }
		    //���½�
		    if(x+1>-1 && x+1<9 && y+1>-1 && y+1<9 && MineArea[x+1][y+1]==0)
			    this.searchAround(x+1,y+1);
			else if(x+1>-1 && x+1<9 && y+1>-1 && y+1<9 && MineArea[x+1][y+1]!=0 && MineArea[x+1][y+1]!=100){
				Mine m1=new Mine(x+1,y+1);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x+1,y+1);	
					}	
			}
	        		
		    //���·�
		    if(x+1>-1 && x+1<9 && y>-1 && y<9 && MineArea[x+1][y]==0)
     			this.searchAround(x+1,y);
		    else if(x+1>-1 && x+1<9 && y>-1 && y<9 && MineArea[x+1][y]!=0 && MineArea[x+1][y]!=100){
		    	Mine m1=new Mine(x+1,y);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x+1,y);	
					}	
		    }
	        	
		    //���½�
		    if(x+1>-1 && x+1<9 && y-1>-1 && y-1<9 && MineArea[x+1][y-1]==0)
				this.searchAround(x+1,y-1);
		    else if(x+1>-1 && x+1<9 && y-1>-1 && y-1<9 && MineArea[x+1][y-1]!=0 && MineArea[x+1][y-1]!=100){
		    	Mine m1=new Mine(x+1,y-1);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x+1,y-1);	
					}	
		    }
	        	
		    //����
    	    if(x>-1 && x<9 && y-1>-1 && y-1<9 && MineArea[x][y-1]==0)
    			this.searchAround(x,y-1);
   	        else if(x>-1 && x<9 && y-1>-1 && y-1<9 && y-1<9 && MineArea[x][y-1]!=0 && MineArea[x][y-1]!=100){
   	        	Mine m1=new Mine(x,y-1);
				//����λ��û�л���ͼƬ�򻭳�ͼƬ
		    	if(!RL.check(m1)){
					   RL.add(m1);				  
				      //���û���ͼƬ�ķ���
				      p.drawPicture(x,y-1);	
					}	
   	        }
	        		
		 
	}

	

	
 
	
	

}

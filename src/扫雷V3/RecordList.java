package ɨ��V3;



public class RecordList {
	private int size=0;
	private Mine []array;
	
	public RecordList(){
		array=new Mine[0];
	}
	public RecordList(int length){
		array=new Mine[length];
	}
	//���Ԫ�صķ���
	public void add(Mine stu){
		//������һ���W�����M�L�Ȟ�ԭ���M��1
		Mine []tempArray=new Mine[size+1];
		//��ԭ���M�е�Ԫ���x�o���M
		for(int i=0;i<size;i++){
			tempArray[i]=array[i];
		}
		//���µ�Ԫ���^�m�xֵ
		tempArray[size]=stu;
		array=tempArray;
		size++;
		
	}
	public void add(Mine stu,int index){
		if(index>=0 && index<size){
		//������һ���W�����M�L�Ȟ�ԭ���M��1
		Mine []tempArray=new Mine[size+1];
		//��ԭ���M�е�Ԫ���x�o���M
		for(int i=0;i<index;i++){
			tempArray[i]=array[i];
		}
		tempArray[index]=stu;
		//�^�m��ԭ���M�е�Ԫ���x�o���M
		for(int i=index+1;i<size+1;i++){
			tempArray[i]=array[i-1];
		}
		array=tempArray;
		size++;	
		}
	}
	
	//����Ԫ��
	public Object set(Mine stu,int index){
		if(index>=0 && index<size){
			Mine s=array[index];
			//������һ���W�����M�L�Ȟ�ԭ���M�L��
			Mine []tempArray=new Mine[size];
			//��ԭ���M�е�Ԫ���x�o���M
			for(int i=0;i<index;i++){
				tempArray[i]=array[i];
			}
			tempArray[index]=stu;
			//�^�m��ԭ���M�е�Ԫ���x�o���M
			for(int i=index+1;i<array.length+1;i++){
				tempArray[i]=array[i];
			}
			array=tempArray;
			return s;
			}
		else 
			return null;	
		
	}
	//ɾ��Ԫ��
	public Object remove(Mine stu){
		int t=0;
		//������һ���W�����M�L�Ȟ�ԭ���M�p1
		Mine []tempArray=new Mine[size-1];
		//����ԭ���M�е�Ԫ�؞�stu��λ��
		for(int i=0;i<size;i++){
			if(array[i].equals(stu)){
				t=i;
				break;		
			}
		}//��ԭ���M�е�Ԫ���x�o���M
		if(t>0){
		for(int i=0;i<t;i++){
	    	tempArray[i]=array[i];	
		}
		
		//�^�m��ԭ���M��ʣ�N��Ԫ���x�o���M
		for(int i=t;i<size-1;i++){
			tempArray[i]=array[i+1];
		}
		}
			
		array=tempArray;
		size--;
		return null;
	}
	//�Ƴ�Ԫ�صķ���
	public Object remove(int index){
		if(index>=0 && index<size){
			Mine s=array[index];
			//������һ���W�����M�L�Ȟ�ԭ���M�p1
			Mine []tempArray=new Mine[size-1];
			//��ԭ���M�е�Ԫ���x�o���M
			for(int i=0;i<index;i++){
				tempArray[i]=array[i];
			}
			//�^�m��ԭ���M��ʣ�N��Ԫ���x�o���M
			for(int i=index;i<size-1;i++){
				tempArray[i]=array[i+1];
			}
			array=tempArray;
			size--;	
			return s;
			}
		else 
			return null;
	}
	//���Ԫ���Ƿ����
	public boolean check(Mine c){
		int cnt=0;
		for(int i=0;i<size;i++)
			if(array[i].getRow()==c.getRow() && array[i].getList()==c.getList()){
				cnt=1;
				break;
			}
		if(cnt==0)
		    return false;
		else 
			return true;
								
	}
	//��ȡԪ��
	public Object get(int index){
		return array[index];
		
	}
	//��ȡԪ������
	public int size(){
		return size;
	}
	



}

package com.leo;

public class MemberSystem {
	
	static int income=0;
	int expend=0;
	
	static int level1=1;
	static int level1count=0;
	static int level2=2;
	static int level2count=0;
	static int level3=3;
	static int level3count=0;

	public static int getRamdomNum(){
		int num=0;
		double temp=Math.random()*10;
		
		while(!(num>0&&num<4)){
			temp=Math.random()*10;
			num=(int) temp;
		}
		
		return num;
	}
	public static void main(String[] args) {
		int memberLevel=0;
		for(int i=0;i<365;i++){
//			System.out.println(getRamdomNum());
			memberLevel=getRamdomNum();
			if(level1==memberLevel){
				income=income+80;
				++level1count;
			}
			if(level2==memberLevel){
				income=income+300;
				++level2count;
			}
			if(level3==memberLevel){
				income=income+3000;
				++level3count;
			}
		}
		System.out.println("income: "+income);
		System.out.println("Common Member Sum: "+level1count);
		System.out.println("Silver Member Sum: "+level2count);
		System.out.println("VIP Member Sum: "+level3count);
		
		System.out.println("--------------------------------- Separator  ---------------------------------------");
		for(int i=0;i<level1count;i++){
//			System.out.println(getRamdomNum());
			memberLevel=getRamdomNum();
			if(level1==memberLevel){
				income=income+80;
				++level1count;
			}
		}
		System.out.println("income: "+income);
		System.out.println("Common Member Sum: "+level1count);
		
		System.out.println("--------------------------------- Separator  ---------------------------------------");
		for(int i=0;i<level2count;i++){
//			System.out.println(getRamdomNum());
			memberLevel=getRamdomNum();
			if(level1==memberLevel){
				income=income+80;
				++level1count;
			}
		}
		System.out.println("income: "+income);
		System.out.println("Common Member Sum: "+level1count);
		System.out.println("--------------------------------- Separator  ---------------------------------------");
		for(int i=0;i<level3count;i++){
//			System.out.println(getRamdomNum());
			memberLevel=getRamdomNum();
			if(level1==memberLevel){
				income=income+80;
				++level1count;
			}
		}
		System.out.println("income: "+income);
		System.out.println("Common Member Sum: "+level1count);

	}

}

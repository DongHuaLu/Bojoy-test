package test;

import java.util.Random;

public class Test8 {

	public static void main(String[] args) {
		for(int i =125 ;i<=125;i++){
			for(int j=160;j<=160;j++){
				for(int k = 30000 ;k<=30000;k++){
					for(int l=4000;l<=4000;l++){
						for(int m = 0 ; m<2;m++){
							System.out.println("自己等级: " + i + " 对方等级: " + j + " 攻击 : "+ k + " 防御: "+ l + " 是否海加防 : " + (m%2==0?" 是 ":" 否 ")  + " 结果: "+ atk63(k,l,i,j,m%2==0));
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * 混天决伤害
	 * 
	 * @param hit
	 * @return
	 */
	public static int atk63(int att,int def,int grade,int hitgrade,boolean ishaishi) {
		att = att < 1 ? 1 : att;
		int atk = att * 3;
		if(ishaishi){
			def = addDef(def,300000,3);
		}
		int _t = 1 * def + 1 * atk;
		int addGrade = grade + hitgrade;
		int _hp = (int) (atk * atk * 1.0 / (_t > 0 ? _t : 1) * (1 + (grade - hitgrade)
				* 1.0 / (addGrade > 0 ? addGrade : 1)));
		if (_hp < 100) {
			_hp = _hp * getRandom(80, 120) / 100;
		} else {
			_hp = getRandom((_hp - 20), (_hp + 20));
		}
		if (_hp < 1) {
			_hp = 1;
		}
		return _hp;
	}
	
	
	/**
	 * 获得在min与max之间随机的一个数,其中包含min和max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		if (min > max) {
			throw new NullPointerException("参数有误,请检参数");
		} else if (min == max) {
			return min;
		}
		int ret = 0;
		if (min < 0) {
			if (max <= 0) {
				ret = (-1) * (new Random().nextInt(Math.abs(min)) + Math.abs(max));
			} else {
				max++;
				ret = (new Random().nextInt(Math.abs(max - min)) + min);
			}
		} else {
			max++;
			ret = new Random().nextInt(max - min) + min;
		}
		return ret;
	}
	
	
	public static int addDef(int def,int intimacy,int level){
		return (int) (def * (intimacy*level*1.0d/(3*(intimacy+5000))+0.5D));
	}
}

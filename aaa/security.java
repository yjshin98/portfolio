package aaa;

import java.security.MessageDigest;

//비밀번호 암호화
public class security {
	MessageDigest md = null;
	String text = null;

	public security(String pass) {		
		try {
			this.text = pass;
			this.md = MessageDigest.getInstance("MD5");
		}
		catch(Exception e) {
			System.out.println("올바른 데이터가 반영되지 않았습니다!");
		}
		
	}
	
	public String md5_se() {
		this.md.update(this.text.getBytes());
		byte word[] = this.md.digest();
		StringBuffer sb = new StringBuffer();
		
		for(int a = 0; a < word.length; a++) {
			sb.append(Integer.toString(word[a] & 0xff));
		}
		this.text = sb.toString();
		return this.text;
	}
	
}


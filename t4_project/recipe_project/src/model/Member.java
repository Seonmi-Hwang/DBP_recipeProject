package model;

public class Member {
	private int member_id;
	private String email_id;
	private String pw;
	private String mname;
	
	public Member() {}
	
	public Member(int member_id, String email_id, String pw, String mname) {
		this(email_id, pw, mname);
		this.member_id = member_id;
	}
	
	public Member(String email_id, String pw, String mname) {
		this.email_id = email_id;
		this.pw = pw;
		this.mname = mname;
	}

	public void update(Member upmember) {
		this.member_id = upmember.member_id;
		this.email_id = upmember.email_id;
		this.pw = upmember.pw;
		this.mname = upmember.mname;
	}
	
	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.pw.equals(password);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Member [member_id=" + member_id + ", email_id=" + email_id + ", pw=" + pw + "mname=" + mname + "]";
	}
	
	
}

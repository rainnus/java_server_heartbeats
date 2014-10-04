package seu.entityclass;

/*
 * 图书馆模块实体类
 */

public class User {

	public String uname;
		private String passwd;

		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
}

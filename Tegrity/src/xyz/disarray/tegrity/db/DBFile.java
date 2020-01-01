package xyz.disarray.tegrity.db;

public class DBFile {
	private String path, md5, sha1;
	
	public DBFile(String path, String md5, String sha1) {
		this.path = path;
		this.md5 = md5;
		this.sha1 = sha1;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getMD5() {
		return md5;
	}
	
	public String getSha1() {
		return sha1;
	}
}

public class User {
	private String userName;
	private long user_public_key;
	private long user_private_key;

	public User(String userName, long user_public_key, long user_private_key) {
		this.userName = userName;

		this.user_public_key = user_public_key;
	}
}

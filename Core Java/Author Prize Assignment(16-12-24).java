package classcode;

interface AuthorPrize {
	String authorWinningPrize(String authorName, int no_of_books);
}

public class LambdaAuthorPrize {

	public static void main(String[] args) {
		String authorName = "Sir Arthur Conan Doyle";
		int noOfBooks = 12;

		AuthorPrize prizeMessage = (author, books) -> {
			if (books > 10) {
				return "Congratulations, " + author + "! You have won the prize for writing " + books + " books!";
			} else {
				return "Sorry, " + author + ". You need to write more than 10 books to win the prize.";
			}
		};

		System.out.println(prizeMessage.authorWinningPrize(authorName, noOfBooks)); 

		String authorName2 = "John Doe";
		int noOfBooks2 = 8;
		System.out.println(prizeMessage.authorWinningPrize(authorName2, noOfBooks2)); 
	}
}

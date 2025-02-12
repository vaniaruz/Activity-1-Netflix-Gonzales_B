import java.util.*;

class Movie {
    private String title;
    private String genre;
    private int duration; 

    public Movie(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public void play() {
        System.out.println("Now playing: " + title + " (" + duration + " min)");
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Genre: " + genre + " | Duration: " + duration + " min";
    }
}

public class netflix {
    private static ArrayList<Movie> movies = new ArrayList<>();
    private static HashMap<String, Integer> watchHistory = new HashMap<>();
    private static HashMap<String, Integer> genrePreferences = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadMovies();
        runCLI();
    }

    private static void loadMovies() {
        movies.add(new Movie("Balota", "Thriller/Drama", 102));
        movies.add(new Movie("Laura", "Drama", 104));
        movies.add(new Movie("Back in Action", "Action/Comedy", 114));
        movies.add(new Movie("Friendly Fire", "Drama/Adventure", 104));
        movies.add(new Movie("Crawl", "Horror/Action", 87));
    }

    private static void runCLI() {
        while (true) {
            System.out.println("\nWelcome to Netflix CLI!");
            System.out.println("1. View Movies");
            System.out.println("2. Search Movie");
            System.out.println("3. Watch Movie");
            System.out.println("4. View Recommendations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    displayMovies();
                    break;
                case 2:
                    searchMovie();
                    break;
                case 3:
                    watchMovie();
                    break;
                case 4:
                    viewRecommendations();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMovies() {
        System.out.println("\nAvailable Movies:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private static void searchMovie() {
        System.out.print("Enter movie title to search: ");
        String title = scanner.nextLine();
        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Movie found: " + movie);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Movie not found.");
        }
    }

    private static void watchMovie() {
        System.out.print("Enter the movie title you want to watch: ");
        String title = scanner.nextLine();
        
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                movie.play();
                watchHistory.put(title, watchHistory.getOrDefault(title, 0) + 1);
                genrePreferences.put(movie.getGenre(), genrePreferences.getOrDefault(movie.getGenre(), 0) + 1);
                return;
            }
        }
        System.out.println("Movie not found.");
    }

    private static void viewRecommendations() {
        if (genrePreferences.isEmpty()) {
            System.out.println("You haven't watched any movies yet. Watch some to get recommendations!");
            return;
        }

        String topGenre = Collections.max(genrePreferences.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("\nBased on your watch history, you might like:");
        
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(topGenre) && !watchHistory.containsKey(movie.getTitle())) {
                System.out.println(movie);
            }
        }
    }
}

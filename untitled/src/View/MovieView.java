package View;

import java.util.List;
import java.util.Scanner;

import Controller.MovieController;
import Model.Movie;

public class MovieView {

    // Controller xử lý các chức năng liên quan đến phim
    private MovieController controller;

    // Dùng để nhập dữ liệu từ bàn phím
    private Scanner scanner;

    public MovieView(MovieController movieController) {
        this.controller = movieController;
        this.scanner = new Scanner(System.in);
    }

    // Hiển thị menu quản lý phim
    public void showMenu() {

        int choice = -1;

        while(choice != 0) {

            System.out.println("\n===== QUẢN LÝ PHIM =====");
            System.out.println("1. Thêm phim");
            System.out.println("2. Sửa phim");
            System.out.println("3. Xóa phim");
            System.out.println("4. Xem danh sách phim");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {

                case 1:
                    addMovie();
                    break;

                case 2:
                    updateMovie();
                    break;

                case 3:
                    deleteMovie();
                    break;

                case 4:
                    displayMovies(controller.getMovies());
                    break;
            }
        }
    }

    // Cập nhật trạng thái phim
    public void updateStatusMenu() {

        System.out.println("\n===== CẬP NHẬT TRẠNG THÁI PHIM =====");

        System.out.print("Nhập mã phim: ");
        String id = scanner.nextLine();

        System.out.println("1. Sắp chiếu");
        System.out.println("2. Đang chiếu");
        System.out.println("3. Ngừng chiếu");
        System.out.print("Chọn: ");

        int choice = Integer.parseInt(scanner.nextLine());

        String status = "";

        switch(choice) {

            case 1:
                status = "Sắp chiếu";
                break;

            case 2:
                status = "Đang chiếu";
                break;

            case 3:
                status = "Ngừng chiếu";
                break;
        }

        controller.updateStatus(id, status);

        System.out.println("Cập nhật thành công!");
    }

    // Thêm phim mới
    private void addMovie() {

        System.out.print("Mã phim: ");
        String id = scanner.nextLine();

        System.out.print("Tên phim: ");
        String name = scanner.nextLine();

        System.out.print("Thể loại: ");
        String genre = scanner.nextLine();

        System.out.print("Thời lượng: ");
        int duration = Integer.parseInt(scanner.nextLine());

        Movie movie =
                new Movie(id, name, genre,
                        duration, "Sắp chiếu");

        controller.addMovie(movie);

        System.out.println("Thêm phim thành công!");
    }

    // Sửa thông tin phim
    private void updateMovie() {

        System.out.print("Mã phim: ");
        String id = scanner.nextLine();

        System.out.print("Tên mới: ");
        String name = scanner.nextLine();

        System.out.print("Thể loại mới: ");
        String genre = scanner.nextLine();

        System.out.print("Thời lượng mới: ");
        int duration = Integer.parseInt(scanner.nextLine());

        controller.updateMovie(
                id,
                name,
                genre,
                duration);

        System.out.println("Sửa phim thành công!");
    }

    // Xóa phim theo mã
    private void deleteMovie() {

        System.out.print("Nhập mã phim cần xóa: ");
        String id = scanner.nextLine();

        if(controller.deleteMovie(id)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Không tìm thấy phim!");
        }
    }

    // Hiển thị danh sách phim hiện có
    public void displayMovies(List<Movie> movies) {

        System.out.println("\n===== DANH SÁCH PHIM =====");

        for(Movie movie : movies) {

            System.out.println(
                    movie.getMovieId()
                    + " | "
                    + movie.getMovieName()
                    + " | "
                    + movie.getStatus()
            );
        }
    }
}
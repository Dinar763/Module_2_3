package view;



import controller.LabelController;
import controller.PostController;
import controller.WriterController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.LabelRepository;
import repository.PostRepository;
import repository.WriterRepository;
import repository.impl.HiberLabelRepositoryImpl;
import repository.impl.HiberPostRepositoryImpl;
import repository.impl.HiberWriterRepositoryImpl;
import service.LabelService;
import service.PostService;
import service.WriterService;
import service.impl.LabelServiceImpl;
import service.impl.PostServiceImpl;
import service.impl.WriterServiceImpl;
import util.HibernateUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner;
    private final WriterView writerView;
    private final PostView postView;
    private final LabelView labelView;
    Session session = HibernateUtil.getCurrentSession();

    public MainView() {
        this.scanner = new Scanner(System.in);
        WriterRepository writerRepository = new HiberWriterRepositoryImpl(session);
        PostRepository postRepository = new HiberPostRepositoryImpl(session);
        LabelRepository labelRepository = new HiberLabelRepositoryImpl(session);

        WriterService writerService = new WriterServiceImpl(writerRepository);
        PostService postService = new PostServiceImpl(postRepository);
        LabelService labelService = new LabelServiceImpl(labelRepository);

        WriterController writerController = new WriterController(writerService);
        PostController postController = new PostController(postService);
        LabelController labelController = new LabelController(labelService);

        this.writerView = new WriterView(writerController, scanner);
        this.postView = new PostView(postController, writerController, labelController, scanner);
        this.labelView = new LabelView(labelController, scanner);
    }

    public void start() {
        boolean flag = false;

        while (!flag) {
            System.out.println("\n+++ Главное меню +++");
            System.out.println("1. Работа с постами");
            System.out.println("2. Работа с авторами");
            System.out.println("3. Работа с лейблами");
            System.out.println("0. Выход");
            System.out.print("Сделайте выбор: \n");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> postView.showMenu();
                    case 2 -> writerView.showMenu();
                    case 3 -> labelView.showMenu();
                    case 0 -> flag = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число");
                scanner.nextLine();
            }
        }
        System.out.println("Программа завершена");
        scanner.close();
    }
}

package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in BootStrap");

        Publisher publisher=new Publisher();
        publisher.setName("Zoya Akhtar Publishing");
        publisher.setCity("Delhi");
        publisher.setState("Maharashta");

        publisherRepository.save(publisher);

        System.out.println("Publisher count: "+publisherRepository.count());

        Author eric=new Author("Eric","Evan");
        Book ddd=new Book("DDD","12345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod=new Author("Rod","Johnson");
        Book  jjj= new Book("JJJ","3123232");
        rod.getBooks().add(jjj);
        jjj.getAuthors().add(rod);

        jjj.setPublisher(publisher);
        publisher.getBooks().add(jjj);

        authorRepository.save(rod);
        bookRepository.save(jjj);
        publisherRepository.save(publisher);

        System.out.println("Number of books "+bookRepository.count());
        System.out.println("Publisher no. of books: "+publisher.getBooks().size());
    }
}

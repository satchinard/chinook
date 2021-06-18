package com.adn.chinook.config;

import com.adn.chinook.entity.Address;
import com.adn.chinook.entity.Album;
import com.adn.chinook.entity.Artist;
import com.adn.chinook.entity.Customer;
import com.adn.chinook.entity.Employee;
import com.adn.chinook.entity.Genre;
import com.adn.chinook.entity.Invoice;
import com.adn.chinook.entity.MediaTypeChinook;
import com.adn.chinook.entity.PlayList;
import com.adn.chinook.entity.Privilege;
import com.adn.chinook.entity.Role;
import com.adn.chinook.entity.Track;
import com.adn.chinook.entity.User;
import com.adn.chinook.repository.GenreRepository;
import com.adn.chinook.repository.PrivilegeRepository;
import com.adn.chinook.repository.RoleRepository;
import com.adn.chinook.repository.UserRepository;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author cagecfi
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
//        Privilege readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        Privilege[] privileges = {readPrivilege, writePrivilege};
//        Set<Privilege> adminPrivileges = Arrays.stream(privileges)
//                .collect(Collectors.toSet());
//
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Collections.singleton(readPrivilege));
//
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        User user = new User();
//        user.setUserName("Test1234");
//        user.setFirstName("Test");
//        user.setLastName("Test");
//        user.setPassword("Test");
//        user.setEmail("test@test.com");
//        user.setRoles(Collections.singleton(adminRole));
//        user.setEnabled(true);
////        user.setPassword(passwordEncoder.encode("test"));
//
//        if (userRepository.findByEmail(user.getEmail()) == null) {
//            userRepository.save(user);
//        }

        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Set<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            role = roleRepository.save(role);
        }
        return role;
    }

    private void saveGenreTestDatas() {
        genreRepository.saveAll(createGenreTestDatas());
    }

    public static List<Genre> createGenreTestDatas() {
        List<Genre> genres = new LinkedList<>();
        genres.add(new Genre(38964, "zanzibar"));
        genres.add(new Genre(8965, "zanzibar"));
        return genres;
    }

    public static List<Address> createAddressTestDatas() {
        List<Address> addresses = new LinkedList<>();
        addresses.add(new Address(65489));
        addresses.add(new Address(65470));
        return addresses;
    }

    public static List<Album> createAlbumTestDatas() {
        List<Album> albums = new LinkedList<>();
        albums.add(new Album(456, "Le monde entier", new Artist(9651, "Ada")));
        albums.add(new Album(389, "L'humanité et nous", new Artist(9654, "Koumah")));
        return albums;
    }

    public static List<Artist> createArtistTestDatas() {
        List<Artist> artists = new LinkedList<>();
        artists.add(new Artist(638, "Abalo"));
        artists.add(new Artist(968, "Nouvi"));
        return artists;
    }

    public static List<Customer> createCustomerTestDatas() {
        List<Customer> customers = new LinkedList<>();
        customers.add(new Customer(1234, "Hello", "Afi", "CAGECFI", new Address(3214)));
        customers.add(new Customer(1235, "Hi", "Komi", "CAFE", new Address(3654)));
        return customers;
    }

    public static List<Employee> createEmployeeTestDatas() {
        List<Employee> employes = new LinkedList<>();
        employes.add(new Employee(45, "Ada", "Komi", "Developer", 4, new Date(),
                new Date(), new Address(4569), new Artist(789, "Skype")));
        employes.add(new Employee(45, "Senu", "Aholou", "Scientific", 4, new Date(),
                new Date(), new Address(2548), new Artist(856, "Zoom")));
        return employes;
    }

    public static List<Invoice> createInvoiceTestDatas() {
        List<Invoice> invoices = new LinkedList<>();
        invoices.add(new Invoice(4563, null, new Date(), null, 789d));
        invoices.add(new Invoice(2654, null, new Date(), null, 965d));
        return invoices;
    }

    public static List<MediaTypeChinook> createMediaTypeTestDatas() {
        List<MediaTypeChinook> mediaTypes = new LinkedList<>();
        mediaTypes.add(new MediaTypeChinook(38964, "Namibie"));
        mediaTypes.add(new MediaTypeChinook(8965, "Zambie"));
        return mediaTypes;
    }

    public static List<PlayList> createPlayListTestDatas() {
        List<PlayList> playLists = new LinkedList<>();
        playLists.add(new PlayList(269, "Slack"));
        playLists.add(new PlayList(159, "Zoom"));
        return playLists;
    }

    public static List<Privilege> createPrivilegeTestDatas() {
        List<Privilege> privileges = new LinkedList<>();
        privileges.add(new Privilege("Slack"));
        privileges.add(new Privilege("Zoom"));
        return privileges;
    }

    public static List<Role> createRoleTestDatas() {
        List<Role> roles = new LinkedList<>();
        roles.add(new Role("Slack"));
        roles.add(new Role("Zoom"));
        return roles;
    }

    public static List<User> createUserTestDatas() {
        List<User> roles = new LinkedList<>();
        roles.add(new User(1l, "user1", "pwd1", "user1", "pwd1",
                "adaro2000@gmail.com", false, false, false, false, false, null));
        roles.add(new User(2l, "user2", "pwd2", "user2", "pwd2",
                "adaro2000@gmail.com", false, false, false, false, false, null));
        return roles;
    }

    public static List<Track> createTrackTestDatas() {
        List<Track> tracks = new LinkedList<>();
        tracks.add(new Track(45, "Happy", "Beethoven", 45, 45, 45d,
                new Genre(4587, "Hip Hop"), new MediaTypeChinook(45, "DVD"), new Album()));
        tracks.add(new Track(49, "Happy", "Beethoven", 49, 49, 55d,
                new Genre(3698, "Hip Hop"), new MediaTypeChinook(98, "CD"), new Album()));
        return tracks;
    }
}

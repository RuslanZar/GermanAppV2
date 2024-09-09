package RuslanCode.germanAppV2.repositories;


import RuslanCode.germanAppV2.model.MyUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM MyUser u WHERE u.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE MyUser u SET u.password = :newPass WHERE u.id = :userId")
    void updatePassword(String newPass, Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE MyUser u SET u.username = 'admin', u.password = :password, u.role = 'ADMIN', u.id = 1000L")
    void saveAdmin(String password);
}

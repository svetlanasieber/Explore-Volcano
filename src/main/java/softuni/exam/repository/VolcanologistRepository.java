package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;

import java.util.Optional;
import java.util.Set;

@Repository
public interface VolcanologistRepository extends JpaRepository<Volcanologist, Long> {


    Optional<Volcanologist> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Volcanologist> findById(Long id);



}

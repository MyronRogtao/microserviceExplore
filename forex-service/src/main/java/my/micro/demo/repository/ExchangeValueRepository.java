package my.micro.demo.repository;

import my.micro.demo.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository  extends JpaRepository<ExchangeValue, Long>{

    ExchangeValue findByFromAndTo(String from, String to);

}

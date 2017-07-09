package ru.kovladimir.carsale.persistence;

import org.hibernate.Session;
import ru.kovladimir.carsale.models.Car;
import ru.kovladimir.carsale.services.CarSearchParameters;
import ru.kovladimir.carsale.services.SessionHibernateFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Car storage.
 */
public class CarStorage implements DAO<Car>, CarRepository{

    private static CarStorage storage = new CarStorage();

    private CarStorage() {
    }

    public static CarStorage getInstance() {
        return storage;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars;
        try (Session session = SessionHibernateFactory.getInstance().openSession()) {
            session.beginTransaction();
            cars = session.createQuery("from Car order by id").list();
            session.getTransaction().commit();
        }
        return cars;
    }

    @Override
    public Car get(int id) {
        Car car;
        try (Session session = SessionHibernateFactory.getInstance().openSession()) {
            session.beginTransaction();
            car = session.get(Car.class, id);
            session.getTransaction().commit();
        }
        return car;
    }

    @Override
    public void create(Car car) {
        try (Session session = SessionHibernateFactory.getInstance().openSession()) {
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Car car) {
        try (Session session = SessionHibernateFactory.getInstance().openSession()) {
            session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Car car) {
        try (Session session = SessionHibernateFactory.getInstance().openSession()) {
            session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Car> getByParameters(CarSearchParameters parameters) {
        List<Car> cars;
        try (Session session = SessionHibernateFactory.getInstance().openSession()) {
            session.beginTransaction();

            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Car> query = builder.createQuery(Car.class);
            Root<Car> carRoot = query.from(Car.class);

            parameters.getCarName().ifPresent(name -> predicates.add(builder.
                    like(carRoot.get("name"),  String.format("%%%s%%", name))));
            parameters.getEngineName().ifPresent(name -> predicates.add(builder.
                    like(carRoot.get("engine").get("name"), String.format("%%%s%%", name))));
            parameters.getTransmissionName().ifPresent(name -> predicates.add(builder.
                    like(carRoot.get("transmission").get("name"), String.format("%%%s%%", name))));
            parameters.getOwnerLogin().ifPresent(login -> predicates.add(builder.
                    equal(carRoot.get("user").get("login"), login)));

            query.select(carRoot);
            query.where(predicates.toArray(new Predicate[]{}));
            cars = session.createQuery(query).list();
            session.getTransaction().commit();
        }
        return cars;
    }


}

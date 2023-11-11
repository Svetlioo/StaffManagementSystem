package com.system.Services;

public interface Service<T> {
    // Using generics :). The T can be any model class, so that this follows SOLID open-closed principle.
    void add(T entity);

    void edit(long id, T newEntity);

    void searchById(long id);

    void searchByName(String name);

    void searchByDepartment(String department);

    void listAll();

    // The delete method will be the "fire an employee command" in the staff service
    void delete(long id);

    void useCommand(String command);

}

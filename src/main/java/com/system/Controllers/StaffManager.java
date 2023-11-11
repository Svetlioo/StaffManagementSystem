package com.system.Controllers;

import com.system.Models.Employee;
import com.system.Services.Service;

public class StaffManager implements Manager {
    private final Service<Employee> service;

    public StaffManager(Service<Employee> service) {
        this.service = service;
    }

    @Override
    public void execute(String command) {
        service.useCommand(command);

    }
}

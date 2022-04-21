package gpse_1_2.access;

import gpse_1_2.access.user.domain.Faculty;

import gpse_1_2.access.user.domain.Role;
import gpse_1_2.access.user.domain.User;
import gpse_1_2.access.user.service.FacultyService;
import gpse_1_2.access.user.service.RoleService;
import gpse_1_2.access.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;


@Service
public class InitializeDatabase implements InitializingBean {

    private final FacultyService facultyService;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public InitializeDatabase(final FacultyService facultyService, final RoleService roleService,
                              final UserService userService) {
        this.facultyService = facultyService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (facultyService.getFaculties().size() == 0) {
            facultyService.addFacultyIfNotExisting("faculty of test 1");
            facultyService.addFacultyIfNotExisting("faculty of test 2");
        }

        if (roleService.getRoles().size() == 0) {
            roleService.addRoleIfNotExisting("Administrator*in");
            roleService.addRoleIfNotExisting("Fakultätsbeauftragte*r");
            roleService.addRoleIfNotExisting("Mitarbeiter*innen");
            roleService.addRoleIfNotExisting("Kursverantwortliche*r");
        }
        if (userService.findAllUser().size() == 0) {
            userService.addUser(new User(50L, "Jan", "Mustermann",
                "jmusterman@google.com", "paswort123",
                null, null, null,
                null, 2));
        }
    }
}

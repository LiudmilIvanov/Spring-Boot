package softuni.workshop.service.services.impl;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import softuni.workshop.data.entities.Role;
import softuni.workshop.data.entities.User;
import softuni.workshop.data.repositories.RoleRepository;
import softuni.workshop.data.repositories.UserRepository;
import softuni.workshop.service.models.UserServiceModel;
import softuni.workshop.service.services.RoleService;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;


@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final RoleService roleService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final RoleRepository roleRepository;
	
	
	@Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, 
    		RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {
		User user = this.modelMapper.map(userRegisterModel, User.class);
		System.out.println();		

		if (this.userRepository.count() == 0) {
			this.roleService.seedRolesInDb();
			user.setAuthorities(this.roleService.findAllRoles().stream()
					.map(r -> this.modelMapper.map(r, Role.class))
					.collect(Collectors.toSet())
					);
		} else {
			user.setAuthorities(new LinkedHashSet<Role>());
			user.getAuthorities().add(this.roleRepository.findByAuthority("USER"));
		}
    	
			user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterModel.getPassword()));
    	return this.modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	return this.userRepository.findByUsername(s);
    }
}

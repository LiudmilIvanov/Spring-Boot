package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AuthorsNameSpace.URI_AUTHORS)
public interface AuthorsNameSpace {

	String URI_AUTHORS = "/authors";
}

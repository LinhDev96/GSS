package entities;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Country {
	@Id
    private int id;

    private String code;

    private String name;

    private List<State> states;
}

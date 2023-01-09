package com.event.manager.security.controller;

import com.event.manager.security.domain.api.MemberDTO;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

/**
 * Role Controller
 */
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = requireNonNull(memberService, "The memberService is required");
    }

    /**
     * Get a member by it's username
     *
     * @param username the member's username to retrieve
     * @return the Member
     * @throws MemberNotFoundException if the specified member username doesn't exist
     */
    @GetMapping("/{username}")
    public MemberDTO getByUsername(@PathVariable(value = "username") String username) throws MemberNotFoundException {
        return this.memberService.getByUsername(username);
    }
}

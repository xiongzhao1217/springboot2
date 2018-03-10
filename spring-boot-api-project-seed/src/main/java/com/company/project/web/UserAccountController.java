package com.company.project.web;
import com.company.project.core.api.Result;
import com.company.project.core.api.ResultGenerator;
import com.company.project.model.UserAccount;
import com.company.project.service.UserAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/03/10.
 */
@RestController
@RequestMapping("/user/account")
public class UserAccountController {
    @Resource
    private UserAccountService userAccountService;

    @PostMapping("/add")
    public Result add(UserAccount userAccount) {
        userAccountService.insertSelective(userAccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userAccountService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(UserAccount userAccount) {
        userAccountService.updateSelective(userAccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserAccount userAccount = userAccountService.selectById(id);
        return ResultGenerator.genSuccessResult(userAccount);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserAccount> list = userAccountService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

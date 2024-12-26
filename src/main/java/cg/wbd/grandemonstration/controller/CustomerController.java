package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.exception.DuplicateEmailException;
import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import cg.wbd.grandemonstration.service.impl.SimpleCustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/inputs-not-acceptable");
    }

    @GetMapping
    public ModelAndView showList() {
        try {
            ModelAndView modelAndView = new ModelAndView("customers/list");
            List<Customer> customers = customerService.findAll();
            modelAndView.addObject("customers", customers);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers/create");
        }
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(Customer customer) throws DuplicateEmailException {
        System.out.println("Hello");
        ModelAndView mv = new ModelAndView("redirect:/customers");
        customerService.save(customer);
        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping
    public String updateCustomer(Customer customer) throws DuplicateEmailException {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}

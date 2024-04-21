package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    List<AddressBook> list();

    void add(AddressBook addressBook);

    AddressBook getDefault();

    void setDefault(AddressBook addressBook);

    void update(AddressBook addressBook);

    void deleteById(Long id);

    AddressBook getById(Long id);
}

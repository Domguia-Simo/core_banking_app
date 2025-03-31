package com.example.DomguiaSimo_BankingApp.AccountOperation.Field;

import java.util.List;

public interface FieldInterface {
    public List<Field> createField(List<Field> field ,Long id);
    public List<Field> getFields();
    public Field getField(Long id);
    public Boolean deleteField(Long id);
    public Boolean updateField(Long id, Field field );
}

package com.abstraction.business;

import com.abstraction.entities.Usuario;
import com.abstraction.persistence.IUsuarioDAO;
import com.abstraction.persistence.impl.UsuarioDAO;

public interface IUsuario_facade {
    public boolean create(Usuario usuario);
    public Usuario validar(Usuario usuario);
}

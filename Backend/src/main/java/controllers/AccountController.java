package controllers;

import dao.AccountDAO;
import exception.ApiException;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import persistence.model.Account;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountController implements IController {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());
    private final AccountDAO dao;

    public AccountController(EntityManagerFactory emf) {
        this.dao = AccountDAO.getInstance(emf);
    }

    @Override
    public Handler getAll() {
        return ctx -> {
            if (!dao.getAll().isEmpty()) {
                ctx.json(dao.getAll());
            } else {
                throw new ApiException(404, "No data found. ", timestamp);
            }
        };
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Account account = dao.getById(id);
            if (account != null) {
                ctx.json(account);
            } else {
                throw new ApiException(404, "No data found. ", timestamp);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            Account accountCreated = ctx.bodyAsClass(Account.class);
            if (accountCreated != null) {
                ctx.json(dao.create(accountCreated));
            } else {
                throw new ApiException(500, "No data found. ", timestamp);
            }
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Account accountToUpdate = ctx.bodyAsClass(Account.class);
            accountToUpdate.setId(id);
            Account accountUpdated = dao.update(accountToUpdate);
            if (accountUpdated != null) {
                ctx.json(accountUpdated);
            } else {
                throw new ApiException(404, "No data found. ", timestamp);
            }
        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Account accountDeleted = dao.delete(id);
            if (accountDeleted != null) {
                ctx.json(accountDeleted);
            } else {
                throw new ApiException(404, "No data found. ", timestamp);
            }
        };
    }
}
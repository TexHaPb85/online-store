package edu.hillel.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.hillel.entities.Item;
import edu.hillel.repository.item.ItemRepository;
import edu.hillel.service.ItemService;

//@WebServlet(urlPatterns = "/items/*")
public class ItemsServlet extends HttpServlet {
    private ItemRepository itemRepository;
    private ItemService itemService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String repositoryName = config.getInitParameter("repository");
        this.itemRepository = ItemRepository.getInstanceByName(repositoryName);
        this.itemService = ItemService.getSingletonInstance(itemRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            Long itemId = Long.parseLong(pathInfo.substring(1));
            Item item = itemService.getItemById(itemId);
            request.setAttribute("item", item);
            request.getRequestDispatcher("/item.jsp").forward(request, response);
        } else {
            List<Item> items = itemService.getAllItems();
            request.setAttribute("goods", items);
            request.getRequestDispatcher("/itemsAll.jsp").forward(request, response);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Item item = new Item();
        item.setItemName(request.getParameter("itemName"));
        item.setDescription(request.getParameter("description"));
        itemService.addNewItem(item);
        response.sendRedirect(request.getContextPath() + "/items/");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete item by ID
        //Long itemId = Long.parseLong(request.getPathInfo().substring(1));
        //itemService.deleteItem(itemId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}

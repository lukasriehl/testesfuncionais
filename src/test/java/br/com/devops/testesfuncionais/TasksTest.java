package br.com.devops.testesfuncionais;

/*
 * @criado em 04/07/2021 - 12:28
 * @autor Lukas Riehl Figueiredo
 */

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.navigate().to("http://localhost:8007/tasks");

        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.navigate().to("http://localhost:8007/tasks");

            //clicar em addTodo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Tarefa registrada com sucesso!", message);
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.navigate().to("http://localhost:8007/tasks");

            //clicar em addTodo
            driver.findElement(By.id("addTodo")).click();

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the task description!", message);
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.navigate().to("http://localhost:8007/tasks");

            //clicar em addTodo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Fill the due date!", message);
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.navigate().to("http://localhost:8007/tasks");

            //clicar em addTodo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/1999");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();

            Assert.assertEquals("Due date must not be in past!", message);
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }
}

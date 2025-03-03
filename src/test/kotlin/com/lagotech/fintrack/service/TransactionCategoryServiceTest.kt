package com.lagotech.fintrack.service

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TransactionCategoryServiceTest {

//    @Mock
//    private lateinit var service: ExpenseCategoryService
//
//    @Mock
//    private lateinit var repository: ExpenseCategoryRepository
//
//    @Mock
//    private lateinit var messageSource: MessageSource
//
//    private val entityToDTOMapper: EntityToDTOMapper = EntityToDTOMapperImpl()
//
//    lateinit var mockExpenseCategory: ExpenseCategoryMock
//
//    @BeforeEach
//    fun setUp() {
//        mockExpenseCategory = ExpenseCategoryMock(entityToDTOMapper)
//        service = ExpenseCategoryService(repository, entityToDTOMapper, messageSource)
//    }
//
//    @Test
//    fun `should save the category when all requirements are correct`() {
//
//        val expectedDTO = mockExpenseCategory.mockExpenseCategoryDTO()
//
//        val entity = entityToDTOMapper.parseObject(expectedDTO, ExpenseCategory::class.java)
//
//        `when`(repository.save(entity)).thenReturn(entity)
//
//        val result = service.save(expectedDTO)
//
//        assertNotNull(result)
//        assertNotNull(entity.id)
//        assertEquals(entity.name, result.name)
//        assertEquals(entity.description, result.description)
//        assertEquals(entity.color, result.color)
//    }
//
//    @Test
//    fun `should return an exception when category already exists`() {
//        val expenseCategory = mockExpenseCategory.mockExpenseCategoryDTO()
//
//        `when`(repository.existsByName(expenseCategory.name)).thenReturn(true)
//        `when`(messageSource.getMessage(anyString(), any(), any()))
//            .thenReturn("Já existe um recurso com o valor informado: ${expenseCategory.name}")
//
//        val exception = assertThrows<IllegalArgumentException> {
//            service.save(expenseCategory)
//        }
//
//        assertEquals("Já existe um recurso com o valor informado: ${expenseCategory.name}", exception.message)
//    }
//
//    @Test
//    fun `should return a category when category name exists`() {
//        val categoryName = "Food"
//        val expenseCategoryList = mockExpenseCategory.mockExpenseCategoryDTOList()
//
//        `when`(repository.findByNameContaining(categoryName)).thenReturn(expenseCategoryList)
//
//        val result = service.findByName(categoryName)
//
//        assertNotNull(result)
//    }
//
//    @Test
//    fun `should return an exception when category name not exists`() {
//        val categoryName = ""
//
//        `when`(repository.findByNameContaining(categoryName)).thenReturn(emptyList())
//        `when`(messageSource.getMessage(anyString(), any(), any()))
//            .thenReturn("Recurso não encontrado")
//
//        val exception = assertThrows<ResourceNotFoundException> {
//            service.findByName(categoryName)
//        }
//
//        assertEquals("Recurso não encontrado", exception.message)
//    }
//
//    @Test
//    fun `should return all categories`() {
//        val expenseCategoryList = mockExpenseCategory.mockExpenseCategoryList()
//
//        `when`(repository.findAll()).thenReturn(expenseCategoryList)
//
//        val result = service.findAll()
//
//        assertNotNull(result)
//    }
//
//    @Test
//    fun `should return an exception when there is no category`() {
//        val expenseCategoryList = emptyList<ExpenseCategory>()
//
//        `when`(repository.findAll()).thenReturn(expenseCategoryList)
//        `when`(messageSource.getMessage(anyString(), any(), any()))
//            .thenReturn("Recurso não encontrado")
//
//        val exception = assertThrows<ResourceNotFoundException> {
//            service.findAll()
//        }
//
//        assertEquals("Recurso não encontrado", exception.message)
//    }
//
//    @Test
//    fun `should return a category when category id exists`() {
//        val id = 1L
//        val entity = mockExpenseCategory.mockExpenseCategory()
//        val expectedDTO: Optional<ExpenseCategoryDTO> =
//            Optional.of(entityToDTOMapper.parseObject(entity, ExpenseCategoryDTO::class.java))
//
//        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
//
//        val result = service.findById(id)
//
//        assertNotNull(result)
//        assertEquals(expectedDTO, result)
//    }
//
//    @Test
//    fun `should return true when category exists by name`() {
//        val categoryName = "Food"
//
//        `when`(repository.existsByName(categoryName)).thenReturn(true)
//
//        val result = service.existsByName(categoryName)
//
//        assertTrue(result)
//    }
//
//    @Test
//    fun `should return false when category not exists by name`() {
//        val categoryName = "NotExists"
//
//        `when`(repository.existsByName(categoryName)).thenReturn(false)
//
//        val result = service.existsByName(categoryName)
//
//        assertEquals(false, result)
//    }
//
//    @Test
//    fun `should update category successfully when id is valid`() {
//        val id = 1L
//        val entity = mockExpenseCategory.mockExpenseCategory()
//        val expectedDTO = entityToDTOMapper.parseObject(entity, ExpenseCategoryDTO::class.java)
//
//        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
//        `when`(repository.save(entity)).thenReturn(entity)
//
//        val result = service.update(id, expectedDTO)
//
//        assertNotNull(result)
//    }
//
//    @Test
//    fun `should thrown an exception on update when category id not exists`() {
//        val id = 99L
//        val entity = mockExpenseCategory.mockExpenseCategory()
//        val expectedDTO = entityToDTOMapper.parseObject(entity, ExpenseCategoryDTO::class.java)
//
//        `when`(repository.findById(id)).thenReturn(Optional.empty())
//        `when`(messageSource.getMessage(anyString(), any(), any()))
//            .thenReturn("Entidade com id $id não encontrada")
//
//        val exception = assertThrows<ResourceNotFoundException> {
//            service.update(id, expectedDTO)
//        }
//
//        assertEquals("Entidade com id $id não encontrada", exception.message)
//    }
//
//    @Test
//    fun `should thrown an exception on delete when category id not exists`() {
//        val id = 99L
//        val entity = mockExpenseCategory.mockExpenseCategory()
//        val expectedDTO = entityToDTOMapper.parseObject(entity, ExpenseCategoryDTO::class.java)
//
//        `when`(repository.findById(id)).thenReturn(Optional.empty())
//        `when`(messageSource.getMessage(anyString(), any(), any()))
//            .thenReturn("Entidade com id $id não encontrada")
//
//        val exception = assertThrows<ResourceNotFoundException> {
//            service.delete(id)
//        }
//
//        assertEquals("Entidade com id $id não encontrada", exception.message)
//    }
}
package com.scaputo.itau_desafio;

import com.scaputo.itau_desafio.business.services.TransactionService;
import com.scaputo.itau_desafio.controller.dtos.TransactionRequestDTO;
import com.scaputo.itau_desafio.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

	@Mock
	private List<TransactionRequestDTO> transactionList;

	@InjectMocks
	private TransactionService transactionService;

	@Test
	public void testAddTransaction() {
		// Arrange
		TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(10.0, OffsetDateTime.now());
		List<TransactionRequestDTO> transactionList = new ArrayList<>();
		transactionService.setTransactionList(transactionList);

		// Act
		transactionService.addTransaction(transactionRequestDTO);

		// Assert
		assertEquals(1, transactionList.size());
	}

	@Test
	public void testAddTransactionWithFutureDate() {
		// Arrange
		TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(10.0, OffsetDateTime.now().plusDays(1));

		// Act e Assert
		assertThrows(UnprocessableEntity.class, () -> transactionService.addTransaction(transactionRequestDTO));
	}

	@Test
	public void testAddTransactionWithNegativeValue() {
		// Arrange
		TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(-10.0, OffsetDateTime.now());

		// Act e Assert
		assertThrows(UnprocessableEntity.class, () -> transactionService.addTransaction(transactionRequestDTO));
	}

	@Test
	public void testCleanTransactions() {
		// Arrange
		TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(10.0, OffsetDateTime.now());
		List<TransactionRequestDTO> transactionList = new ArrayList<>();
		transactionService.setTransactionList(transactionList);
		// Act
		transactionService.cleanTransactions();

		// Assert
		assertEquals(0, transactionList.size());
	}

	@Test
	public void testSearchTransactions() {
		// Arrange
		List<TransactionRequestDTO> transactionList = new ArrayList<>();
		transactionService.setTransactionList(transactionList);
		transactionList.add(new TransactionRequestDTO(10.0, OffsetDateTime.now()));
		transactionList.add(new TransactionRequestDTO(20.0, OffsetDateTime.now()));

		// Act
		List<TransactionRequestDTO> result = transactionService.searchTransactions(60);

		// Assert
		assertEquals(2, result.size());
	}
}
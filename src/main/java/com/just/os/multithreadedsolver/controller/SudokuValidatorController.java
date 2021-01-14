package com.just.os.multithreadedsolver.controller;

import com.just.os.multithreadedsolver.multithreaded.MTSudokuValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("sudoku")
public class SudokuValidatorController {

    @PostMapping("validate")
    public ResponseEntity<Boolean> validateSudoku(@RequestBody LinkedHashMap<String, ArrayList<ArrayList<Integer>>> sudoku_grid_list) {
        ArrayList<ArrayList<Integer>> sudokuList = sudoku_grid_list.get("sudoku_grid");
        int[][] sudokuArr = sudokuList.stream().map(u -> u.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
        MTSudokuValidator validator = new MTSudokuValidator(sudokuArr);
        return ResponseEntity.status(HttpStatus.OK).body(validator.validate());
    }
}

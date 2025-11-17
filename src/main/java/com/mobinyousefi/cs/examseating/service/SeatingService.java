package com.mobinyousefi.cs.examseating.service;

import com.mobinyousefi.cs.examseating.dao.AllocationDao;
import com.mobinyousefi.cs.examseating.dao.ExamDao;
import com.mobinyousefi.cs.examseating.dao.RoomDao;
import com.mobinyousefi.cs.examseating.dao.StudentDao;
import com.mobinyousefi.cs.examseating.model.Allocation;
import com.mobinyousefi.cs.examseating.model.Exam;
import com.mobinyousefi.cs.examseating.model.Room;
import com.mobinyousefi.cs.examseating.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Core service that generates exam seating allocations.
 * Strategy: sort students by roll number, then fill rooms sequentially.
 */
public class SeatingService {

    private final StudentDao studentDao;
    private final RoomDao roomDao;
    private final ExamDao examDao;
    private final AllocationDao allocationDao;

    public SeatingService(StudentDao studentDao, RoomDao roomDao, ExamDao examDao, AllocationDao allocationDao) {
        this.studentDao = studentDao;
        this.roomDao = roomDao;
        this.examDao = examDao;
        this.allocationDao = allocationDao;
    }

    public List<Allocation> generateAndPersistAllocations(int examId) throws SQLException {
        // ensure exam exists
        List<Exam> exams = examDao.findAll();
        boolean examExists = exams.stream().anyMatch(e -> e.getId() == examId);
        if (!examExists) {
            throw new IllegalArgumentException("Exam with id=" + examId + " not found");
        }

        List<Student> students = studentDao.findAll();
        List<Room> rooms = roomDao.findAll();

        if (students.isEmpty() || rooms.isEmpty()) {
            return List.of();
        }

        List<Allocation> allocations = new ArrayList<>();
        Iterator<Student> it = students.iterator();

        for (Room room : rooms) {
            int seat = 1;
            while (seat <= room.getCapacity() && it.hasNext()) {
                Student s = it.next();
                Allocation a = new Allocation();
                a.setExamId(examId);
                a.setStudentId(s.getId());
                a.setRoomId(room.getId());
                a.setSeatNumber(seat);
                allocations.add(a);
                seat++;
            }
            if (!it.hasNext()) {
                break;
            }
        }

        // replace existing allocations for this exam
        allocationDao.deleteByExamId(examId);
        allocationDao.insertAll(allocations);

        return allocationDao.findByExamId(examId);
    }
}

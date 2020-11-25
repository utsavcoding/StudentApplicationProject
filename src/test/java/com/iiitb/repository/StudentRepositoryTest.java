package com.iiitb.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentRepositoryTest {

    //private static Session session =  Mockito.mock(Session.class);
    private static Query query = Mockito.mock(Query.class);
    //private static NativeQuery nativeQuery = Mockito.mock(NativeQuery.class);

    @InjectMocks
    StudentRepository studentRepository= Mockito.spy(new StudentRepository());

    @Mock
    NativeQuery nativeQuery;

    @Mock
    Session session;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NoResultException.class)
    public void testFindByRollNumberForException() throws Exception {
        Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenThrow(new NoResultException());
        studentRepository.findByRollNumber("MT20129831");
    }

    @Test
    public void testFetchCreditByStudentId() throws Exception {
        //StringBuilder sql=new StringBuilder();
        List<Double> list=new ArrayList<Double>();
        list.add(3.2);
        Double expected=3.2;
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(nativeQuery);
        Mockito.when(nativeQuery.list()).thenReturn(list);
        Double actual=studentRepository.fetchCreditByStudentId(1);
        assertEquals(expected,actual);
    }

    @Test
    public void testFetchStudentSpecializationDetailWhenPresent() throws Exception {
        List<Object> list=new ArrayList<Object>();
        Object obj1[]=new Object[4];Object obj2[]=new Object[4];
        obj1[0]="Aman";obj1[1]=13.0;obj1[2]="DS";obj1[3]=16.0;
        obj2[0]="Aman";obj2[1]=16.0;obj2[2]="NW";obj2[3]=16.0;
        list.add(obj1);list.add(obj2);
        String expected="NW";
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(nativeQuery);
        Mockito.when(nativeQuery.list()).thenReturn(list);
        String actual=studentRepository.fetchStudentSpecializationDetail(1);
        assertEquals(expected,actual);
    }

    @Test
    public void testFetchStudentSpecializationDetailWhenPresentButNoSpecialization() throws Exception {
        List<Object> list=new ArrayList<Object>();
        Object obj1[]=new Object[4];Object obj2[]=new Object[4];
        obj1[0]="Aman";obj1[1]=13.0;obj1[2]="DS";obj1[3]=16.0;
        obj2[0]="Aman";obj2[1]=14.0;obj2[2]="NW";obj2[3]=16.0;
        list.add(obj1);list.add(obj2);
        String expected=null;
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(nativeQuery);
        Mockito.when(nativeQuery.list()).thenReturn(list);
        String actual=studentRepository.fetchStudentSpecializationDetail(1);
        assertEquals(expected,actual);
    }
}
